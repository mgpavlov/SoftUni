package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import mostwanted.domain.entities.Racer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarImportDto {
    @Expose
    private String brand;
    @Expose
    private String model;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer yearOfProduction;
    @Expose
    private String racerName;
    @Expose
    private Double zeroToSixty;
    @Expose
    private Double maxSpeed;

    public CarImportDto() {
    }

    @NotNull
    public String getBrand() {
        return this.brand;
    }

    @NotNull
    public String getModel() {
        return this.model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
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

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
