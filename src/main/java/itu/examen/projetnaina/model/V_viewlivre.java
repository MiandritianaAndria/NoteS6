package itu.examen.projetnaina.model;

import jakarta.persistence.*;

@Entity
@Table(name ="v_viewlivre")
public class V_viewlivre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_livre;

    String titre;

    int livreview;

    public V_viewlivre() {
    }

    public V_viewlivre(int id_livre, String titre, int livreview) {
        this.id_livre = id_livre;
        this.titre = titre;
        this.livreview = livreview;
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

    public int getLivreview() {
        return livreview;
    }

    public void setLivreview(int livreview) {
        this.livreview = livreview;
    }
}
