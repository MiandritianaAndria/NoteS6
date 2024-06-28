package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Categorie;
import itu.examen.projetnaina.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie saveCategorie(Categorie adherant) {
        return categorieRepository.save(adherant);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
