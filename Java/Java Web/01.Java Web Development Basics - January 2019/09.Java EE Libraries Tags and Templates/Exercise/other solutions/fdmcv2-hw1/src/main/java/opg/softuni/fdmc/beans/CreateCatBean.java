package opg.softuni.fdmc.beans;

import opg.softuni.fdmc.domain.dto.CreateCatDto;
import opg.softuni.fdmc.services.CatService;
import opg.softuni.fdmc.util.ApplicationUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Named(value = "createCat")
public class CreateCatBean {
    private final CatService catService;

    private final CreateCatDto createCatDto;

    @Inject
    public CreateCatBean(CatService employeeService, CreateCatDto createCatDto) {
        this.catService = employeeService;
        this.createCatDto = createCatDto;
    }

    public String getName() {
        return this.createCatDto.getName();
    }

    public void setName(String name) {
        this.createCatDto.setName(name);
    }

    public String getBreed() {
        return this.createCatDto.getBreed();
    }

    public void setBreed(String breed) {
        this.createCatDto.setBreed(breed);
    }

    public String getColor() {
        return this.createCatDto.getColor();
    }

    public void setColor(String color) {
        this.createCatDto.setColor(color);
    }

    public Byte getAge() {
        return this.createCatDto.getAge();
    }

    public void setAge(Byte age) {
        this.createCatDto.setAge(age);
    }

    public String getSex() {
        return this.createCatDto.getSex();
    }

    public void setSex(String sex) {
        this.createCatDto.setSex(sex);
    }

    public BigDecimal getPrice() {
        return this.createCatDto.getPrice();
    }

    public void setPrice(BigDecimal price) {
        this.createCatDto.setPrice(price);
    }

    public Date getAddedOn() {
        return this.createCatDto.getAddedOn();
    }

    public void setAddedOn(Date addedOn) {
        this.createCatDto.setAddedOn(addedOn);
    }

    public boolean isHasPassport() {
        return this.createCatDto.isHasPassport();
    }

    public void setHasPassport(boolean hasPassport) {
        this.createCatDto.setHasPassport(hasPassport);
    }

    public void create(){
        this.catService.createCat(this.createCatDto);
        ApplicationUtils.redirect("/cats-all.xhtml");
    }
}
