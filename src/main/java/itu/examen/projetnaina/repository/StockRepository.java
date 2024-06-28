package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByIdlivre(Long id);

    @Modifying
    @Query("UPDATE Stock s SET s.quantite = :newStock WHERE s.idlivre = :id")
    void updateQuantiteByIdlivre(@Param("newStock") int newStock, @Param("id") Long id);
}
