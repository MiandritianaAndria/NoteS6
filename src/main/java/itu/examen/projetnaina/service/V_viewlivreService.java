package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.V_viewlivre;
import itu.examen.projetnaina.repository.V_viewlivreRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class V_viewlivreService {

    private final V_viewlivreRepository v_viewlivreRepository;

    @Autowired
    public V_viewlivreService(V_viewlivreRepository repository) {
        this.v_viewlivreRepository = repository;
    }

    public List<V_viewlivre> findAll() {
        return v_viewlivreRepository.findAll();
    }

    public String affLivreView(HttpServletRequest request, Model model)
    {
        List<V_viewlivre> viewlivres = findAll();
        model.addAttribute("viewlivres", viewlivres);

        HttpSession session = request.getSession();
        model.addAttribute("usertype", session.getAttribute("usertype"));

        return "indexAdmin";
    }
}

