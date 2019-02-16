package fdmcapp.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "cats")
public class Cat extends BaseEntity {

    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addOn;
    private boolean hasPassport;

    public Cat() {
    }

    @Column(nullable = false, updatable = false)
    @Size(min = 2, max = 10)
    public String getName() {
        return this.name;
    }

    @Column(nullable = false)
    @Size(min = 5, max = 20)
    public String getBreed() {
        return this.breed;
    }

    @Column
    public String getColor() {
        return this.color;
    }

    @Column(nullable = false)
    @Min(1)
    @Max(31)
    public Integer getAge() {
        return this.age;
    }

    @Column
    public String getGender() {
        return this.gender;
    }

    @Column(nullable = false)
    @DecimalMin("0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    @Column
    public Date getAddOn() {
        return this.addOn;
    }

    @Column(nullable = false)
    public boolean isHasPassport() {
        return this.hasPassport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAddOn(Date addOn) {
        this.addOn = addOn;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
