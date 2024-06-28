package itu.examen.projetnaina.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "adherant")
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String designation;
    int sanction;
    int quotient;

    @Column(name = "niveaux_lecture")
    int niveauxlecture;

    @Column(name = "niveaux_emporte")
    int niveauxemporte;

    public Adherant() {
    }

    public Adherant(Long id, String designation, int sanction, int quotient, int niveauxlecture, int niveauxemporte) {
        this.id = id;
        this.designation = designation;
        this.sanction = sanction;
        this.quotient = quotient;
        this.niveauxlecture = niveauxlecture;
        this.niveauxemporte = niveauxemporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSanction() {
        return sanction;
    }

    public void setSanction(int sanction) {
        this.sanction = sanction;
    }

    public int getQuotient() {
        return quotient;
    }

    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }

    public int getNiveauxlecture() {
        return niveauxlecture;
    }

    public void setNiveauxlecture(int niveauxlecture) {
        this.niveauxlecture = niveauxlecture;
    }

    public int getNiveauxemporte() {
        return niveauxemporte;
    }

    public void setNiveauxemporte(int niveauxemporte) {
        this.niveauxemporte = niveauxemporte;
    }
}
