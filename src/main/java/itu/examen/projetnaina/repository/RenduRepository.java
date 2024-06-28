package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Rendu;
import itu.examen.projetnaina.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenduRepository extends JpaRepository<Rendu, Long> {
    List<Rendu> findAllByIdMembre(int id);
}
