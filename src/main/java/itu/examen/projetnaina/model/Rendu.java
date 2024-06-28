package itu.examen.projetnaina.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rendu")
public class Rendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_membre")
    int idMembre;

    @Column(name = "id_livre")
    int idLivre;
    @Column(name = "date_rendu")
    Date dateRendu;
    Date penalite;

    public Rendu() {
    }

    public Rendu(Long id, int idMembre, int idLivre, Date dateRendu, Date penalite) {
        this.id = id;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateRendu = dateRendu;
        this.penalite = penalite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    public Date getPenalite() {
        return penalite;
    }

    public void setPenalite(Date penalite) {
        this.penalite = penalite;
    }
}
