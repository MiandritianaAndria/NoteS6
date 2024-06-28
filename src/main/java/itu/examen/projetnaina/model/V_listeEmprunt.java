package itu.examen.projetnaina.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "v_listeemprunt")
public class V_listeEmprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    int id_livre;
    String titre;
    @Column(name = "id_membre")
    int idmembre;

    @Column(name = "referencemembre")
    String refmembre;

    String nom;
    Date date_debut;
    Date date_fin;

    int emporter;

    int statue;

    public V_listeEmprunt() {
    }

    public V_listeEmprunt(int id, int id_livre, String titre, int idmembre, String refmembre, String nom, Date date_debut, Date date_fin, int emporter, int statue) {
        this.id = id;
        this.id_livre = id_livre;
        this.titre = titre;
        this.idmembre = idmembre;
        this.refmembre = refmembre;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.emporter = emporter;
        this.statue = statue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(int idmembre) {
        this.idmembre = idmembre;
    }

    public String getRefmembre() {
        return refmembre;
    }

    public void setRefmembre(String refmembre) {
        this.refmembre = refmembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
