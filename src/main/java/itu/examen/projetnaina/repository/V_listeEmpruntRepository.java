package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Emprunt;
import itu.examen.projetnaina.model.V_listeEmprunt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface V_listeEmpruntRepository extends JpaRepository<V_listeEmprunt, Long> {

    List<V_listeEmprunt> findAllByStatue(int statue);

    List<V_listeEmprunt> findAllByStatueAndIdmembre(int i, int id_membre);


    List<V_listeEmprunt> findAllByStatueAndRefmembre(int i, String refMembre);
}
