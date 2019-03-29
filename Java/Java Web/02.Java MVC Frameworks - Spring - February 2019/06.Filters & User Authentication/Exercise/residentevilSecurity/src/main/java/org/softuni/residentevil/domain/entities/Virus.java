package org.softuni.residentevil.domain.entities;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {
    private String name;
    private String description;
    private String sideEffects;
    private boolean isDeadly;
    private boolean isCurable;
    private Creator creator;
    private Mutation mutation;
    private Magnitude magnitude;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private LocalDate releasedOn;
    private List<Capital> capitals;

    @Column(nullable = false, unique = true, length = 10)
    @Length(min = 3, max = 10)
    public String getName() {
        return this.name;
    }

    @Column(nullable = false, columnDefinition = "TEXT", length = 100)
    @Length(min = 5, max = 100)
    public String getDescription() {
        return this.description;
    }

    @Column(length = 50)
    public String getSideEffects() {
        return this.sideEffects;
    }

    @Column(nullable = false)
    public boolean isDeadly() {
        return this.isDeadly;
    }

    @Column(nullable = false)
    public boolean isCurable() {
        return this.isCurable;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Creator getCreator() {
        return this.creator;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Mutation getMutation() {
        return this.mutation;
    }

    @Column(nullable = false)
    @Range(min = 0, max = 100)
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    @Column(nullable = false)
    @Range(min = 1, max = 12)
    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    @Column(nullable = false)
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    @ManyToMany
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = {@JoinColumn(name = "virus_id")},
            inverseJoinColumns = {@JoinColumn(name = "capital_id")})
    public List<Capital> getCapitals() {
        return this.capitals;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
