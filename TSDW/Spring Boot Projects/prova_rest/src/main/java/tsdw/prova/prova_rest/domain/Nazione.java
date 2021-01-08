package tsdw.prova.prova_rest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Nazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String lingua;

    public Nazione() {
    }

    public Nazione(@NotBlank String name, @NotBlank String lingua) {
        this.name = name;
        this.lingua = lingua;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public String toString() {
        return "Nazione{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lingua='" + lingua + '\'' +
                '}';
    }
}
