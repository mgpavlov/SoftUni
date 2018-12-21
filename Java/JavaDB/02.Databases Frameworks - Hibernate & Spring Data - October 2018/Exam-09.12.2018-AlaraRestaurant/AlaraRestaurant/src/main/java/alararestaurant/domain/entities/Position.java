package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.List;


@Entity(name = "positions")
public class Position extends BaseEntity {

    private String name;
    private List<Employee> employees;

    public Position() {
        this.employees = new ArrayList<>();
    }

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }


    @OneToMany(mappedBy = "position", targetEntity = Employee.class)
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
