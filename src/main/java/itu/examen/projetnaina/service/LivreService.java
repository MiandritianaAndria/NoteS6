package itu.examen.projetnaina.service;

import itu.examen.projetnaina.service.*;
import itu.examen.projetnaina.model.*;
import itu.examen.projetnaina.repository.LivreRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private RenduService renduService;

    @Autowired
    private StockService stockService;

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private CategorieService categorieService;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public void saveLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public void updateLivre(Long id, Livre livre) {
        livreRepository.save(livre);
    }

    public List<Livre> filtreLivre(String search) {
        return livreRepository.filtreLivre(search);
    }

    public int getLastId() {
        return livreRepository.findAll()
                .stream()
                .mapToInt(Livre::getId)
                .max()
                .orElse(0);
    }

    public String livreAdmin(String message, String succes, String search, HttpServletRequest request, Model model) {
        if (message != null) {
            model.addAttribute("error", message);
        }
        if (succes != null) {
            model.addAttribute("succes", succes);
        }

        List<Livre> livres = getAllLivres();

        HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session
        if (session != null) {
            Membre membre = (Membre) session.getAttribute("membre");
            model.addAttribute("membre", membre);

        } else {
            model.addAttribute("membre", "vide");
        }

        if (!search.equals("vide")) {
            livres = filtreLivre(search);
        }
        model.addAttribute("livres", livres);

        model.addAttribute("usertype", session.getAttribute("usertype"));

        return "livreAdmin";
    }

    public String livreClient(String search, Model model) {
        List<Livre> livres = getAllLivres();
        if (!search.equals("vide")) {
            livres = filtreLivre(search);
        }
        model.addAttribute("livres", livres);
        return "livre";
    }

    public String detailLivre(Long id, Model model) {
        Livre livre = getLivreById(id).orElse(null);
        model.addAttribute("livredetail", livre);
        return "livreDetails";
    }

    public String empruntLivre(Long id, String emporter, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            redirectAttributes.addFlashAttribute("error", "Ajouter une session");
            return "redirect:/livreAdmin";
        }

        Membre membre = (Membre) session.getAttribute("membre");

        if (membre == null) {
            redirectAttributes.addFlashAttribute("error", "Membre non trouve dans la session.");
            return "redirect:/livreAdmin";
        }

        List<Rendu> rendus = renduService.findByMembre(membre.getId());
        Rendu renduRecent = rendus.stream()
                .filter(rendu -> rendu.getDateRendu() != null)
                .max(Comparator.comparing(Rendu::getDateRendu))
                .orElse(null);

        if (renduRecent != null && renduRecent.getPenalite() != null) {
            LocalDate penaliteDate = renduRecent.getPenalite().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (penaliteDate.isAfter(LocalDate.now())) {
                long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), penaliteDate);
                redirectAttributes.addFlashAttribute("error", "Vous devez attendre encore " + daysBetween + " jours avant de pouvoir prendre un nouveau livre");
                return "redirect:/livreAdmin";
            }
        }

        Stock stock = stockService.findByIdlivre(id);

        if (stock.getQuantite() == 0) {
            redirectAttributes.addFlashAttribute("error", "Cette exemplaire est epuise");
            return "redirect:/livreAdmin";
        }

        Adherant typeAdherant = adherantService.getAdherantById(membre.getIdadherant());

        // Supposons que membre.getDate_naissance() retourne un objet Date
        Date dateNaissance = membre.getDatenaissance();

        // Convertir la Date en LocalDate
        LocalDate dateNaissanceLocalDate = dateNaissance.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Obtenir la date actuelle en LocalDate
        LocalDate today = LocalDate.now();

        // Calculer l'âge du membre
        int age = (int) ChronoUnit.YEARS.between(dateNaissanceLocalDate, today);

        Livre livre = getLivreById(id).orElse(null);

        if (age < livre.getAge_requis()) {
            redirectAttributes.addFlashAttribute("error", "Vous n'avez pas l'âge requis pour emprunter ce livre");
            return "redirect:/livreAdmin";
        }

        if (typeAdherant.getNiveauxlecture() < livre.getType_utilisation()) {
            redirectAttributes.addFlashAttribute("error", "Votre type d'adhesion vous permet pas de lire ce livre");
            return "redirect:/livreAdmin";
        }

        int validationEmporter = 0;

        if (emporter != null && !emporter.isEmpty()) {
            if (livre.getEmporter() == 0) {
                redirectAttributes.addFlashAttribute("error", "Vous ne pouvez pas emporter ce livre mais vous pouvez le lire");
                return "redirect:/livreAdmin";
            }
            if (typeAdherant.getNiveauxemporte() < livre.getType_emporter()) {
                redirectAttributes.addFlashAttribute("error", "Vous ne pouvez pas emporter ce livre mais vous pouvez le lire");
                return "redirect:/livreAdmin";
            }

            validationEmporter = 1;

        }

        Date dateFin = Date.from(
                LocalDate.now().plusDays(typeAdherant.getQuotient()).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        Emprunt emprunt = new Emprunt();

        emprunt.setId_livre(livre.getId());
        emprunt.setId_membre(membre.getId());
        emprunt.setDate_debut(new Date());
        emprunt.setDate_fin(dateFin);
        emprunt.setEmporter(validationEmporter);

        empruntService.addEmprunt(emprunt);

        int newStock = stock.getQuantite() - 1;
        stockService.updateQuantiteByIdlivre(newStock, id);

        redirectAttributes.addFlashAttribute("succes", "Emprunt reussis");
        return "redirect:/livreAdmin";
    }

    public void getCathégorieAdhérant(Model model) {
        List<Categorie> categories = categorieService.getAllCategorie();
        model.addAttribute("categories", categories);

        List<Adherant> adherants = adherantService.getAllAdherantsLecture();
        model.addAttribute("adherants", adherants);
    }

    public void addLivre(String titre,
                         String auteur,
                         String collection,
                         String resume,
                         int categorie,
                         String tome,
                         String motCles,
                         String langue,
                         String code,
                         String isbn,
                         String edition,
                         String dateEdition,
                         int nbpage,
                         MultipartFile couverture,
                         int ageMin,
                         int typeLire,
                         String emporte,
                         int typeEmporte,
                         int quantite,
                         RedirectAttributes redirectAttributes, Model model) {
        String[] formats = {"jpg", "png", "jpeg"};

        Livre livre = new Livre();

        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setCollection(collection);
        livre.setResume(resume);
        livre.setId_categorie(categorie);
        livre.setTome(tome);
        livre.setMotcle(motCles);
        livre.setLangue(langue);
        livre.setNum_code(code);
        livre.setIsbn(isbn);
        livre.setEdition(edition);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateEdition);
            livre.setDate_edition(date);
            try {
                livre.setDate_edition(formatter.parse(dateEdition));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Invalid date format");
            }

            livre.setNb_page(nbpage);
            livre.setAge_requis(ageMin);
            livre.setType_utilisation(typeLire);
            livre.setEmporter(Integer.parseInt(emporte));
            livre.setType_emporter(typeEmporte);

            Path path = Paths.get(
                    "src/main/resources/static/public/imgCouverture");
            String fileName = couverture.getOriginalFilename();
            Path filePath = path.resolve(fileName);

            String imgPath = "public/imgCouverture/" + fileName;
            Path resolvedPath = path.resolve(imgPath);
            livre.setCouverture(imgPath);

            try {
                Files.copy(couverture.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            saveLivre(livre);

            int idlivre = getLastId();

            Stock stock = new Stock();

            stock.setIdlivre(idlivre);
            stock.setQuantite(quantite);
            stockService.saveStock(stock);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

