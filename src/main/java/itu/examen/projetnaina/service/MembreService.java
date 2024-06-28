package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Membre;
import itu.examen.projetnaina.model.User;
import itu.examen.projetnaina.repository.MembreRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MembreService {

    @Autowired
    private MembreRepository membreRepository;

    public void saveMembre(Membre membre) {
        membreRepository.save(membre);
    }

    public void deleteMembre(Long id) {
        membreRepository.deleteById(id);
    }

    public List<Membre> getAllMembres() {
        return membreRepository.findAll();
    }

    public void updateMembre(Long id, Membre membre) {
        membreRepository.save(membre);
    }

    public Optional<Membre> findById(Long id) {
        return membreRepository.findById(id);
    }

    public Membre findByReferenceMembre(String refMembre) {
        return membreRepository.findByReferenceMembre(refMembre);
    }

    public Membre findByTelephone(String telephone)
    {
        return membreRepository.findByTelephone(telephone);
    }

    public String verifClient(HttpServletRequest request, String reference, Model model)
    {
        Membre user = findByReferenceMembre(reference);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("userReference", user.getReferenceMembre());
            model.addAttribute("userReference", session.getAttribute("userReference"));
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid référence");
            return "redirect:/connexion";
        }

    }

    public String deconnexion(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.removeAttribute("userReference");

        return "redirect:/";
    }

    public void recupMembre(HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session
        if (session != null && session.getAttribute("userReference") != null ) {
            model.addAttribute("userReference", session.getAttribute("userReference"));
        } else {
            model.addAttribute("userReference", "vide");
        }
    }
    public String refMembre(HttpServletRequest request, String refMembre, RedirectAttributes redirectAttributes, Model model)
    {
        HttpSession session = request.getSession();
        Membre membre = findByReferenceMembre(refMembre);
        if (membre != null) {
            session.setAttribute("membre", membre);
            redirectAttributes.addFlashAttribute("membre", membre);
        }else{
            return "redirect:/livreAdmin?message=loose";
        }
        return "redirect:/livreAdmin";
    }

    public String effacerSession(HttpServletRequest request,RedirectAttributes redirectAttributes,Model model)
    {
        HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session
        if (session != null) {
            Membre membre = (Membre) session.getAttribute("membre");
            session.removeAttribute("membre");
        }
        return "redirect:/livreAdmin";
    }

    public String ajoutMembre(String nom,
                              String adresse,
                              String telephone,
                              Date dateN,
                              int adherant,Model model)
    {
        Membre existingUser = findByTelephone(telephone);

        if (existingUser != null && existingUser.getTelephone().equals(telephone)) {
            model.addAttribute("error", "telephone already exists");
            return "inscription";
        } else {

            Date dateToday = new Date();

            StringBuilder sb = new StringBuilder();
            sb.append("ref");
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                if (random.nextInt(2) == 0) {
                    sb.append((char)('0' + random.nextInt(10)));
                } else {
                    sb.append((char)('A' + random.nextInt(26)));
                }
            }
            String refClient = sb.toString();

            Membre membreInsert = new Membre();
            membreInsert.setReferenceMembre(refClient);
            membreInsert.setNom(nom);
            membreInsert.setAdresse(adresse);
            membreInsert.setTelephone(telephone);
            membreInsert.setIdadherant(adherant);
            membreInsert.setDatenaissance(dateN);
            membreInsert.setDateinscription(dateToday);

            saveMembre(membreInsert);   // Save the user to the database
            model.addAttribute("membreInsert", membreInsert);
        }

        return "redirect:/inscription";
    }
}

