package tsdw.serious_attempt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"role", "user_email"})})
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String role;

    @ManyToOne
    private Users user;

    public Roles() {
    }

    public Roles(@NotBlank String role, Users user) {
        this.role = role;
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
