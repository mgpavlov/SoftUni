package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity(name = "cars")
public class Car extends BaseEntity {
    /*•	brand – a string (required).
•	model – a string (required).
•	price – a decimal data type.
•	yearOfProduction – an integer number (required).
•	maxSpeed – a floating-point data type.
•	zeroToSixty – a floating-point data type.
•	racer – a Racer entity.
*/
    private String brand;
    private String model;
    private BigDecimal price;
    private Integer yearOfProduction;
    private Double maxSpeed;
    private Double zeroToSixty;
    private Racer racer;

    public Car() {
    }

    @Column(name = "brand", nullable = false)
    public String getBrand() {
        return this.brand;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return this.model;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    @Column(name = "year_of_production", nullable = false)
    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    @Column(name = "max_speed")
    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    @Column(name = "zero_to_sixty")
    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    @ManyToOne(targetEntity = Racer.class)
    @JoinColumn(
            name = "racer_id",
            referencedColumnName = "id"
    )
    public Racer getRacer() {
        return this.racer;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
