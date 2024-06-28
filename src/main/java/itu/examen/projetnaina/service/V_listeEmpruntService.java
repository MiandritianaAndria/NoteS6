package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Membre;
import itu.examen.projetnaina.model.V_listeEmprunt;
import itu.examen.projetnaina.repository.V_listeEmpruntRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class V_listeEmpruntService {

    @Autowired
    V_listeEmpruntRepository v_listeEmpruntRepository;

    @Autowired
    private MembreService membreService;

    public List<V_listeEmprunt> findAllByStatue(int statue) {
        return v_listeEmpruntRepository.findAllByStatue(statue);
    }

    public List<V_listeEmprunt> findAllByStatueAndIdmembre(int i, int id_membre) {
        return v_listeEmpruntRepository.findAllByStatueAndIdmembre(i, id_membre);
    }

    public List<V_listeEmprunt> findAllByStatueAndRefmembre(int i, String ref) {
        return v_listeEmpruntRepository.findAllByStatueAndRefmembre(i, ref);
    }

    public V_listeEmprunt findById(Long id){
        return v_listeEmpruntRepository.findById(id).orElse(null);
    }

    public void save(V_listeEmprunt v_listeEmprunt){
        v_listeEmpruntRepository.save(v_listeEmprunt);
    }

    public void delete(Long id){
        v_listeEmpruntRepository.deleteById(id);
    }

    public List<V_listeEmprunt> listeEmprunt(String refMembre, String succes, HttpServletRequest request, Model model)
    {
        List<V_listeEmprunt> emprunts = findAllByStatue(0);
        System.out.println("count table emprunt..........................................."+emprunts.size());

        if(refMembre != null && !refMembre.isBlank()) {
            Membre membre = membreService.findByReferenceMembre(refMembre);
            emprunts = findAllByStatueAndIdmembre(0, membre.getId());
        }

        if (succes != null) {
            model.addAttribute("succes", succes);
        }

        HttpSession session = request.getSession();
        model.addAttribute("usertype", session.getAttribute("usertype"));
        model.addAttribute("emprunts", emprunts);

        return emprunts;
    }

    public void listeEmpruntClient(HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession(false);
        String refMembre = (String)session.getAttribute("userReference");

        List<V_listeEmprunt> emprunts = findAllByStatueAndRefmembre(0, refMembre);

        model.addAttribute("emprunts", emprunts);
    }
}

