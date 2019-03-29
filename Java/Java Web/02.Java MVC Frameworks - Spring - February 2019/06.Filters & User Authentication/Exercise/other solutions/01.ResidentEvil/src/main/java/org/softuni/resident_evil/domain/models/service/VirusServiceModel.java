package org.softuni.resident_evil.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.softuni.resident_evil.domain.enums.Magnitude;
import org.softuni.resident_evil.domain.enums.Mutation;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

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

    private Integer hoursUntilMutation;

    private Magnitude magnitude;

    private LocalDate releasedOn;

    private Set<Long> capitals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @NotNull
    @Length(min = 3, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @NotNull
    @Length(min = 5, max = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(max = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Pattern(regexp = "^corp|Corp$")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @NotNull
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Min(0)
    @Max(100)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Min(1)
    @Max(12)
    public Integer getHoursUntilMutation() {
        return hoursUntilMutation;
    }

    public void setHoursUntilMutation(Integer hoursUntilMutation) {
        this.hoursUntilMutation = hoursUntilMutation;
    }

    @NotNull
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Past
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Long> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Long> capitals) {
        this.capitals = capitals;
    }


}
