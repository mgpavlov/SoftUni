package org.softuni.resident_evil.domain.entities;

import org.softuni.resident_evil.domain.enums.Magnitude;
import org.softuni.resident_evil.domain.enums.Mutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {
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

    private Set<Capital> capitals;

    @Column(nullable = false, unique = true, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(length = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Column(nullable = false, length = 5)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(nullable = false)
    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }


    @Column(nullable = false)
    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Column(nullable = false)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Column(nullable = false)
    public Integer getHoursUntilMutation() {
        return hoursUntilMutation;
    }

    public void setHoursUntilMutation(Integer hoursUntilMutation) {
        this.hoursUntilMutation = hoursUntilMutation;
    }

    @Column(nullable = false)
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Column(nullable = false)
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @ManyToMany
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = {@JoinColumn(name = "virus_id")},
            inverseJoinColumns = {@JoinColumn(name = "capital_id")})
    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
