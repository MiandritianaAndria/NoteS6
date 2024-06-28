package itu.examen.projetnaina.repository;

import itu.examen.projetnaina.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndMdp(String email, String mdp);
    
}
