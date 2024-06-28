package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Adherant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Long>{
}
