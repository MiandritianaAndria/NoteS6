package itu.examen.projetnaina.controller;


import itu.examen.projetnaina.model.*;
import itu.examen.projetnaina.repository.*;

import itu.examen.projetnaina.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.text.SimpleDateFormat;

import java.util.UUID;
import java.util.Date;
import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;


@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private MembreService membreService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private RenduService renduService;

    @Autowired
    private StockService stockService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private V_listeEmpruntService v_listeEmpruntService;

    @Autowired
    private V_viewlivreService v_viewlivreService;

    @GetMapping("/")
    public String home(HttpServletRequest request,Model model) {
        model.addAttribute("message", "Hello, Spring MVC!");
        membreService.recupMembre(request,model);
        return "index";
    }

    @GetMapping("/gretting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "gretting";
    }

    @GetMapping("/person")
    public String showForm(User person) {
        return "person";
    }

    @PostMapping("/person")
    public String checkPersonInfo(@RequestParam("email") String email,
                                  @RequestParam("mdp") String mdp, Model model) {

        User existingUser = userService.findByEmail(email);

        if (existingUser != null && existingUser.getEmail().equals(email)) {
            model.addAttribute("error", "Email already exists");

            return "person";
        } else {

            User personInsert = new User();
            personInsert.setEmail(email);
            personInsert.setMdp(mdp);

            userService.save(personInsert);   // Save the user to the database
            model.addAttribute("person", personInsert);
        }

        return "result";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/connexion")
    public String loginClient(Model model)
    {
        return "connexionClient";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request)
    {
        String redirect = membreService.deconnexion(request);
        return redirect;
    }

    @PostMapping("/loginClientVerif")
    public String loginClientVerif(HttpServletRequest request,@RequestParam("reference") String reference, Model model)
    {
        String redirect = membreService.verifClient(request,reference,model);
        return redirect;
    }

    @PostMapping("/loginVerif")
    public String loginVerif(HttpServletRequest request,@RequestParam("email") String email,
                             @RequestParam("mdp") String mdp, Model model) {

        String redirect = userService.verifUser(request , email,  mdp, model);
        return redirect;

    }

    @GetMapping("/indexAdmin")
    public String indexAdmin(HttpServletRequest request,Model model) {

        String redirect = v_viewlivreService.affLivreView(request , model);
        return redirect;
    }

    @GetMapping("/livreAdmin")
    public String livreAdmin(@RequestParam(name = "message", required = false) String message,
                             @RequestParam(name = "succes", required = false) String succes,@RequestParam(name="search", required=false, defaultValue="vide") String search,
                             HttpServletRequest request,Model model) {

        String redirect = livreService.livreAdmin(message, succes, search, request,model);
        return redirect;
    }

    @GetMapping("/refMembre")
    public String refMembre(HttpServletRequest request, @RequestParam("refMembre") String refMembre,RedirectAttributes redirectAttributes,Model model) {
        
        String redirect = membreService.refMembre(request, refMembre, redirectAttributes, model);
        return redirect;
    }

    @GetMapping("/effacerSession")
    public String effacerSession(HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {

        String redirect = membreService.effacerSession(request, redirectAttributes, model);
        return redirect;
    }

    @GetMapping("/inscription")
    public String inscription(Model model) {
        List<Adherant> adherants = adherantService.getAllAdherants();
        model.addAttribute("adherants", adherants);
        return "inscription";
    }

    @PostMapping("/ajouterMembre")
    public String ajouterMembre(@RequestParam("nom") String nom,
                                @RequestParam("adresse") String adresse,
                                @RequestParam("telephone") String telephone,
                                @RequestParam("dateN") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateN,
                                @RequestParam("adherant") int adherant,Model model) {

        String redirect = membreService.ajoutMembre( nom,  adresse,  telephone,  dateN,  adherant, model);
        return redirect;
    }

    @GetMapping("/livre")
    public String livre(HttpServletRequest request,@RequestParam("search") String search,Model model) {

        System.out.println("eto le search.............."+search);
        String redirect = livreService.livreClient(search, model);
        membreService.recupMembre(request,model);
        return redirect;

    }
    @GetMapping("/detailLivre")
    public String detailLivre(@RequestParam("id") Long id, Model model) {

        String redirect = livreService.detailLivre(id, model);
        return redirect;
    }

    @PostMapping("/reserverLivre")
    public String addlivre(@RequestParam("id") Long id,
                           @RequestParam(name = "emporter", required = false) String emporter,
                           HttpServletRequest request, RedirectAttributes redirectAttributes,
                           Model model) {

        String redirect = livreService.empruntLivre(id, emporter, request, redirectAttributes, model);
        return redirect;
    }

    @GetMapping("/listeEmprunt")
    public String listeEmprun(@RequestParam(name = "refMembre", required = false) String refMembre,
                              @RequestParam(name = "succes" , required = false) String succes,
                              HttpServletRequest request,Model model) {

        List<V_listeEmprunt> emprunts = v_listeEmpruntService.listeEmprunt(refMembre,succes,request,model);

        return "listeEmprun";
    }

    @GetMapping("/empruntClient")
    public String listeEmprunClient(HttpServletRequest request,Model model) {

        v_listeEmpruntService.listeEmpruntClient(request,model);
        membreService.recupMembre(request,model);
        return "listeEmprunClient";
    }


    @PostMapping("/rendu")
    public String rendu(@RequestParam(name = "idEmprunt") String idEmprunt,
                        @RequestParam(name = "idLivre") String idLivre,
                        @RequestParam(name = "idMembre") String idMembre,
                        @RequestParam(name = "dateRetour", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRetour,
                        RedirectAttributes redirectAttributes,Model model) {

        renduService.rendu(idEmprunt,idLivre,idMembre,dateRetour,redirectAttributes,model);

        return "redirect:/listeEmprunt";
    }

    @GetMapping("/formulaireLivre")
    public String formulaireLivre(Model model) {

        livreService.getCathégorieAdhérant(model);

        return "addLivre";
    }

    @PostMapping("/addlivre")
    public String addlivre(@RequestParam(name = "titre") String titre,
                           @RequestParam(name = "auteur") String auteur,
                           @RequestParam(name = "collection") String collection,
                           @RequestParam(name = "resume") String resume,
                           @RequestParam(name = "categorie") int categorie,
                           @RequestParam(name = "tome") String tome,
                           @RequestParam(name = "motCles") String motCles,
                           @RequestParam(name = "langue") String langue,
                           @RequestParam(name = "code") String code,
                           @RequestParam(name = "isbn") String isbn,
                           @RequestParam(name = "edition") String edition,
                           @RequestParam(name = "dateEdition") String dateEdition,
                           @RequestParam(name = "nbpage") int nbpage,
                           @RequestParam(name = "couverture") MultipartFile couverture,
                           @RequestParam(name = "ageMin") int ageMin,
                           @RequestParam(name = "typeLire") int typeLire,
                           @RequestParam(name = "emporte") String emporte,
                           @RequestParam(name = "typeEmporte") int typeEmporte,
                           @RequestParam(name = "quantite") int quantite,
                           RedirectAttributes redirectAttributes,Model model) {

        livreService.addLivre( titre, auteur, collection, resume, categorie, tome, motCles, langue, code, isbn, edition, dateEdition, nbpage, couverture, ageMin, typeLire, emporte, typeEmporte, quantite, redirectAttributes, model);
        return "redirect:/formulaireLivre";
    }

}
