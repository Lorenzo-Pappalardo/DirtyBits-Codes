package tsdw.prova.prova_rest.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Citta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Nazione nazione;

    @NotBlank
    private String nome;

    @NotNull
    private Integer abitanti;

    public Citta() {
    }

    public Citta(Nazione nazione, @NotBlank String nome, @NotNull Integer abitanti) {
        this.nazione = nazione;
        this.nome = nome;
        this.abitanti = abitanti;
    }

    public Integer getId() {
        return id;
    }

    public Nazione getNazione() {
        return nazione;
    }

    public void setNazione(Nazione nazione) {
        this.nazione = nazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(Integer abitanti) {
        this.abitanti = abitanti;
    }

    @Override
    public String toString() {
        return "Citta{" +
                "id=" + id +
                ", nazione=" + nazione +
                ", nome='" + nome + '\'' +
                ", abitanti=" + abitanti +
                '}';
    }
}
