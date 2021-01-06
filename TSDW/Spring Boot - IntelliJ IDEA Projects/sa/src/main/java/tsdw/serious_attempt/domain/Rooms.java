package tsdw.serious_attempt.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<Users> usersList;

    public Rooms() {
    }

    public Rooms(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Integer getId() {
        return id;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", usersList=" + usersList +
                '}';
    }
}