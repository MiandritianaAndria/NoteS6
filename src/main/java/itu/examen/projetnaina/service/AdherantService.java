package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Adherant;
import itu.examen.projetnaina.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdherantService {
    @Autowired
    private AdherantRepository adherantRepository;

    public List<Adherant> getAllAdherants() {
        return adherantRepository.findAll(Sort.by(Sort.Direction.DESC, "sanction"));
    }

    public List<Adherant> getAllAdherantsLecture() {
        return adherantRepository.findAll(Sort.by(Sort.Direction.ASC, "niveauxlecture"));
    }

    public Adherant getAdherantById(Long id) {
        return adherantRepository.findById(id).orElse(null);
    }

    public Adherant saveAdherant(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    public void deleteAdherant(Long id) {
        adherantRepository.deleteById(id);
    }

}
