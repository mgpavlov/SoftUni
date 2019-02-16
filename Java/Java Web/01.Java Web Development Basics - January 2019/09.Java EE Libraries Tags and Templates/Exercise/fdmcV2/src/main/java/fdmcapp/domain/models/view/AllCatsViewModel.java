package fdmcapp.domain.models.view;

import java.math.BigDecimal;
import java.util.Date;

public class AllCatsViewModel {

    private String id;
    private String name;
    private String breed;
    private String color;
    private String gender;
    private BigDecimal price;
    private Date addOn;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddOn() {
        return this.addOn;
    }

    public void setAddOn(Date addOn) {
        this.addOn = addOn;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
