package itu.examen.projetnaina.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emprunt")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int id_livre;
    int id_membre;
    Date date_debut;
    Date date_fin;

    int emporter;

    int statue;

    public Emprunt() {
    }
    public Emprunt(Long id, int id_livre, int id_membre, Date date_debut, Date date_fin, int emporter, int statue) {
        this.id = id;
        this.id_livre = id_livre;
        this.id_membre = id_membre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.emporter = emporter;
        this.statue = statue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getEmporter() {
        return emporter;
    }

    public void setEmporter(int emporter) {
        this.emporter = emporter;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }
}
