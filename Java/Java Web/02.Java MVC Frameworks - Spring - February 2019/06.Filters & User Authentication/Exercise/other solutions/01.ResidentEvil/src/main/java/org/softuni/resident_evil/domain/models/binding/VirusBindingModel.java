package org.softuni.resident_evil.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.resident_evil.domain.enums.Magnitude;
import org.softuni.resident_evil.domain.enums.Mutation;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.util.Set;

@MappedSuperclass
public abstract class VirusBindingModel {

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

    private Set<Long> capitals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Length(min = 3, max = 10, message = "Invalid name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5, max = 100, message = "Invalid description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(max = 50, message = "Invalid side effect")
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Pattern(regexp = "^corp|Corp$", message = "Invalid creator")
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

    @NotNull(message = "Mutation cannot be null")
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Min(value = 0, message = "Invalid turnover rate")
    @Max(value = 100, message = "Invalid turnover rate")
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Min(value = 1, message = "Invalid hours")
    @Max(value = 12, message = "Invalid hours")
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

    @NotEmpty(message = "You must select capitals")
    public Set<Long> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Long> capitals) {
        this.capitals = capitals;
    }
}
