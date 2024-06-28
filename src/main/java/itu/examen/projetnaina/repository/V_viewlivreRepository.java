package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.V_viewlivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface V_viewlivreRepository extends JpaRepository<V_viewlivre, Long> {
}
