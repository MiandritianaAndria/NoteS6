package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.Livre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

    @Query(" (SELECT l FROM Livre l WHERE LOWER(l.titre) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.auteur) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.motcle) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.collection) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.resume) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.langue) LIKE %:search%) " +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.edition) LIKE %:search%)" +
            "UNION " +
            "(SELECT l FROM Livre l WHERE LOWER(l.num_code) LIKE %:search%)")
    List<Livre> filtreLivre(@Param("search") String search);

}
