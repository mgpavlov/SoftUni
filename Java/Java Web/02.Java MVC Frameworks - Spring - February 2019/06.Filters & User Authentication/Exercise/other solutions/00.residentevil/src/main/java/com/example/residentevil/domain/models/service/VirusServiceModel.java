package com.example.residentevil.domain.models.service;

import com.example.residentevil.domain.entities.Capitals;
import com.example.residentevil.domain.entities.enumerations.Magnitude;
import com.example.residentevil.domain.entities.enumerations.Mutation;
import com.example.residentevil.util.validators.annotations.CreatorValidation;
import com.example.residentevil.util.validators.annotations.DateValidation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class VirusServiceModel {

    private String id;
    private String name;
    private String description;
    private String sideEffects;
    private String creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUtilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private List<Capitals> capitals;

    public VirusServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 10, message = "Cannot be empty, should be between 3 and 10 symbols.")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 100, message = "Cannot be empty, should be between 5 and 100 symbols.")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Size(max = 50, message = "Should have a maximum of 50 symbols")
    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @CreatorValidation
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return this.isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @NotNull(message = "Cannot be null.")
    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Min(value = 0, message = "Turnover rate cannot be less than 0")
    @Max(value = 100, message = "Turnover rate cannot be greater than 100")
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Min(value = 1, message = "Hours util turn cannot be less than 1")
    @Max(value = 12, message = "Hours util turn cannot be greater than 12")
    public Integer getHoursUtilTurn() {
        return this.hoursUtilTurn;
    }

    public void setHoursUtilTurn(Integer hoursUtilTurn) {
        this.hoursUtilTurn = hoursUtilTurn;
    }

    @NotNull(message = "Cannot be null.")
    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @DateValidation
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capitals> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capitals> capitals) {
        this.capitals = capitals;
    }
}
