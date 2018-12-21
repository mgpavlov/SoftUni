package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
public class Employee extends BaseEntity {

    private String name;
    private Integer age;
    private Position position;
    private List<Order> orders;

    public Employee() {
        this.orders = new ArrayList<>();
    }

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @Column(name = "age", nullable = false)
    @Min(15)
    @Max(80)
    public Integer getAge() {
        return this.age;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Position getPosition() {
        return this.position;
    }

    @OneToMany(mappedBy = "employee", targetEntity = Order.class)
    public List<Order> getOrders() {
        return this.orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
