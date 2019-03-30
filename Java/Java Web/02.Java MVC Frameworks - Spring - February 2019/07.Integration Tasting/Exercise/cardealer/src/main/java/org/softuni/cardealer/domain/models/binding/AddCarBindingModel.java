package org.softuni.cardealer.domain.models.binding;

import org.softuni.cardealer.domain.entities.Part;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AddCarBindingModel {

    private String make;

    private String model;

    private Long travelledDistance;

    private List<String> parts;

    public AddCarBindingModel() {
        this.parts = new ArrayList<>();
    }

    @NotNull
    @NotEmpty
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotNull
    @NotEmpty
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @NotNull
    public List<String> getParts() {
        return this.parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }
}
