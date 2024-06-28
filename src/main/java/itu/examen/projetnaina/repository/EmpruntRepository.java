package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Adherant;
import itu.examen.projetnaina.model.Emprunt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findAllByStatue(int statue);

    @Transactional
    @Modifying
    @Query("UPDATE Emprunt e SET e.statue = :statue WHERE e.id = :id")
    void updateStatue(Long id, int statue);
}
