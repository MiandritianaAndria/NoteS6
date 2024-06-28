package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Adherant;
import itu.examen.projetnaina.model.Emprunt;
import itu.examen.projetnaina.model.Rendu;
import itu.examen.projetnaina.model.Stock;
import itu.examen.projetnaina.repository.RenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RenduService {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private MembreService membreService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private StockService stockService;

    @Autowired
    private AdherantService adherantService;

    public List<Rendu> findAll() {
        return renduRepository.findAll();
    }

    public Rendu findById(Long id) {
        return renduRepository.findById(id).orElse(null);
    }

    public Rendu save(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    public void delete(Long id) {
        renduRepository.deleteById(id);
    }

    public List<Rendu> findByMembre(int id) {
        return renduRepository.findAllByIdMembre(id);
    }

    public void rendu(String idEmprunt, String idLivre, String idMembre, Date dateRetour, RedirectAttributes redirectAttributes, Model model)
    {
        empruntService.updateStatue(Long.parseLong(idEmprunt), 1);

        Rendu rendu = new Rendu();

        rendu.setIdLivre(Integer.parseInt(idLivre));
        rendu.setIdMembre(Integer.parseInt(idMembre));

        Emprunt emprunt = empruntService.findById(Long.parseLong(idEmprunt));

        Date dateEmpruntFin = emprunt.getDate_fin();

        if(dateRetour != null)
        {
            rendu.setDateRendu(dateRetour);
            if(dateEmpruntFin.before(dateRetour))
            {
                Adherant adherant = adherantService.getAdherantById(
                        Long.valueOf(
                                membreService.findById(Long.valueOf(idMembre))
                                        .orElseThrow(() -> new NoSuchElementException("Membre not found"))
                                        .getIdadherant()
                        )
                );

                int difference = (int) ((rendu.getDateRendu().getTime() - emprunt.getDate_fin().getTime()) / (1000 * 60 * 60 * 24));
                int timeBonus = difference * adherant.getSanction();

                LocalDate currentDate = rendu.getDateRendu().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Ajouter le timeBonus à la date actuelle
                LocalDate newDate = currentDate.plusDays(timeBonus);
                Date newDateAsDate = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                rendu.setPenalite(newDateAsDate);

            }else{
                rendu.setDateRendu(dateRetour);
                rendu.setPenalite(new Date());
            }
        }else{
            rendu.setDateRendu(new Date());
            rendu.setPenalite(new Date());
        }

        save(rendu);

        Stock stock = stockService.findByIdlivre(Long.valueOf(idLivre));

        int newStock = stock.getQuantite() + 1;
        stockService.updateQuantiteByIdlivre(newStock, Long.valueOf(idLivre));

        redirectAttributes.addFlashAttribute("succes", "Livre restitué");
    }
}

