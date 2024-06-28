package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Adherant;
import itu.examen.projetnaina.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
    Membre findByTelephone(String telephone);

    @Query(" select m from Membre m where m.referenceMembre = :refMembre")
    Membre findByReferenceMembre(@Param("refMembre") String refMembre);

    Adherant findById(int id);

}

