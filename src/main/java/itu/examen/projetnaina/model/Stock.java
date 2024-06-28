package itu.examen.projetnaina.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantite")
    int quantite;
    @Column(name = "idlivre")
    int idlivre;

    public Stock() {
    }

    public Stock(Long id, int quantite, int id_livre) {
        this.id = id;
        this.quantite = quantite;
        this.idlivre = id_livre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdlivre() {
        return idlivre;
    }

    public void setIdlivre(int id_livre) {
        this.idlivre = id_livre;
    }
}
