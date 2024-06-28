package itu.examen.projetnaina.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String referenceMembre;
    String nom;

    String adresse;
    long idadherant ;
    String telephone;
    Date datenaissance;
    Date dateinscription;

    public Membre() {
    }

    public Membre(int id, String referenceMembre, String nom, String adresse, int idadherant, String telephone, Date datenaissance, Date dateinscription) {
        this.id = id;
        this.referenceMembre = referenceMembre;
        this.nom = nom;
        this.adresse = adresse;
        this.idadherant = idadherant;
        this.telephone = telephone;
        this.datenaissance = datenaissance;
        this.dateinscription = dateinscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferenceMembre() {
        return referenceMembre;
    }

    public void setReferenceMembre(String referenceMembre) {
        this.referenceMembre = referenceMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getIdadherant() {
        return idadherant;
    }

    public void setIdadherant(int idadherant) {
        this.idadherant = idadherant;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Date getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(Date dateinscription) {
        this.dateinscription = dateinscription;
    }
}
