package com.example.residentevil.domain.entities;

import com.example.residentevil.domain.entities.enumerations.Magnitude;
import com.example.residentevil.domain.entities.enumerations.Mutation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

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
    private Integer hoursUtilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private List<Capitals> capitals;

    public Virus() {
    }

    @Column(name = "name", nullable = false, length = 10)
    @Size(min = 3, max = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition="TEXT")
    @Size(min = 5, max = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "side_effect", length = 50)
    @Size(max = 50)
    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Column(name = "creator")
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "is_deadly")
    public Boolean getDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    @Column(name = "is_curable")
    public Boolean getCurable() {
        return this.isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @Column(name = "mutation", nullable = false)
    @Enumerated(EnumType.STRING)
    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Column(name = "turnover_rate")
    @Min(0)
    @Max(100)
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Column(name = "hours_util_turn")
    @Min(1)
    @Max(12)
    public Integer getHoursUtilTurn() {
        return this.hoursUtilTurn;
    }

    public void setHoursUtilTurn(Integer hoursUtilTurn) {
        this.hoursUtilTurn = hoursUtilTurn;
    }

    @Column(name = "magnitude", nullable = false)
    @Enumerated(EnumType.STRING)
    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Column(name = "released_on", columnDefinition="DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @ManyToMany(targetEntity = Capitals.class)
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id")
    )
    public List<Capitals> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capitals> capitals) {
        this.capitals = capitals;
    }
}
