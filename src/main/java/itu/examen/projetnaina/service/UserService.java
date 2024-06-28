package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Livre;
import itu.examen.projetnaina.model.Membre;
import itu.examen.projetnaina.model.User;
import itu.examen.projetnaina.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    
    public User verifEmailAndPassword(String email, String mdp) {
        return userRepository.findByEmailAndMdp(email, mdp);
    }
    public String verifUser(HttpServletRequest request, String email, String mdp, Model model)
    {
        User user = verifEmailAndPassword(email,mdp);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());

            if(user.getType() == 2){
                session.setAttribute("usertype", user.getType());
                model.addAttribute("usertype", session.getAttribute("usertype"));
                return "redirect:/indexAdmin";
            }
            if(user.getType() == 1){
                session.setAttribute("usertype", user.getType());
                model.addAttribute("usertype", session.getAttribute("usertype"));
                return "redirect:/livreAdmin";
            }

        } else {
            model.addAttribute("error", "Invalid information");
            return "redirect:/login";
        }
        return "redirect:/login";
    }

}

