package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Emprunt;
import itu.examen.projetnaina.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService {

    @Autowired
    EmpruntRepository empruntRepository;

    public void addEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

    public List<Emprunt> findAll(){
        return empruntRepository.findAll();
    }

    public Emprunt findById(Long id){
        return empruntRepository.findById(id).orElse(null);
    }

    public void deleteEmprunt(Long id){
        empruntRepository.deleteById(id);
    }

    public void updateEmprunt(Long id, Emprunt emprunt){
        emprunt.setId(id);
        empruntRepository.save(emprunt);
    }

    public List<Emprunt> findAllByStatue(int statue){
        return empruntRepository.findAllByStatue(statue);
    }

    public void updateStatue(Long id, int statue){
        empruntRepository.updateStatue(id, statue);
    }
}

