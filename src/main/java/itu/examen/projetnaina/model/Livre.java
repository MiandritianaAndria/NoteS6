package itu.examen.projetnaina.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String titre;
    String auteur;
    String collection;

    String resume;
    int id_categorie;
    String tome;
    String motcle;
    String langue;
    String num_code;
    String isbn;
    String edition;
    Date date_edition;
    int nb_page;
    String couverture;
    int age_requis;
    int type_utilisation;
    int emporter;

    int type_emporter;

    public Livre() {
    }

    public Livre(int id, String titre, String auteur, String collection, String resume, int id_categorie, String tome, String motcle, String langue, String num_code, String isbn, String edition, Date date_edition, int nb_page, String couverture, int age_requis, int type_utilisation, int emporter, int type_emporter) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.collection = collection;
        this.resume = resume;
        this.id_categorie = id_categorie;
        this.tome = tome;
        this.motcle = motcle;
        this.langue = langue;
        this.num_code = num_code;
        this.isbn = isbn;
        this.edition = edition;
        this.date_edition = date_edition;
        this.nb_page = nb_page;
        this.couverture = couverture;
        this.age_requis = age_requis;
        this.type_utilisation = type_utilisation;
        this.emporter = emporter;
        this.type_emporter = type_emporter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getTome() {
        return tome;
    }

    public void setTome(String tome) {
        this.tome = tome;
    }

    public String getMotcle() {
        return motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNum_code() {
        return num_code;
    }

    public void setNum_code(String num_code) {
        this.num_code = num_code;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getDate_edition() {
        return date_edition;
    }

    public void setDate_edition(Date date_edition) {
        this.date_edition = date_edition;
    }

    public int getNb_page() {
        return nb_page;
    }

    public void setNb_page(int nb_page) {
        this.nb_page = nb_page;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public int getAge_requis() {
        return age_requis;
    }

    public void setAge_requis(int age_requis) {
        this.age_requis = age_requis;
    }

    public int getType_utilisation() {
        return type_utilisation;
    }

    public void setType_utilisation(int type_utilisation) {
        this.type_utilisation = type_utilisation;
    }

    public int getEmporter() {
        return emporter;
    }

    public void setEmporter(int emporter) {
        this.emporter = emporter;
    }

    public int getType_emporter() {
        return type_emporter;
    }

    public void setType_emporter(int type_emporter) {
        this.type_emporter = type_emporter;
    }
}
