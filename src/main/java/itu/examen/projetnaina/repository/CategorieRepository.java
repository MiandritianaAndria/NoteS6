package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Categorie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
