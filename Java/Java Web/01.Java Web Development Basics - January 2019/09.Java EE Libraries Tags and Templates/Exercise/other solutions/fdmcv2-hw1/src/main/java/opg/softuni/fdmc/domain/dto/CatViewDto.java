package opg.softuni.fdmc.domain.dto;

import opg.softuni.fdmc.domain.entities.Cat;

import java.math.BigDecimal;
import java.util.Date;

public class CatViewDto {
    private String name;

    private String breed;

    private String color;

    private Byte age;

    private String sex;

    private BigDecimal price;

    private Date addedOn;

    public CatViewDto() {
    }

    public CatViewDto(Cat cat) {
        this.initFields(cat);
    }

    private void initFields(Cat cat){
        if(cat == null){
            return;
        }
        this.name = cat.getName();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
        this.age = cat.getAge();
        this.sex = cat.getSex();
        this.price = cat.getPrice();
        this.addedOn = cat.getAddedOn();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }
}
