package tsdw.serious_attempt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
public class Salaries {
    @Id
    private String role;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Float salary;

    public Salaries() {
    }

    public Salaries(String role, @NotNull @Digits(integer = 10, fraction = 2) Float salary) {
        this.role = role;
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salaries{" +
                "role=" + role +
                ", salary=" + salary +
                '}';
    }
}
