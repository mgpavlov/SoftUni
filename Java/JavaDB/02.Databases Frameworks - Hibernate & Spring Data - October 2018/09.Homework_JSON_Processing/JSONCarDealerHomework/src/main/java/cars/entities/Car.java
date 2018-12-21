package cars.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    private String make;

    @Basic
    private String model;

    @Column(name = "travelled_distance")
    private long travelledDistance;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cars_parts",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"),
            foreignKey = @ForeignKey(name = "fk_cars_parts_cars"),
            inverseForeignKey = @ForeignKey(name = "fk_cars_parts_parts"))
    private Set<Part> parts;

    @OneToOne(mappedBy = "car")
    private Sale sales;

    public Car() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return this.parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Sale getSales() {
        return this.sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }
}
