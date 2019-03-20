package com.softuni.residentevil.domain.etities;

import com.softuni.residentevil.domain.enums.Magnitude;
import com.softuni.residentevil.domain.enums.Mutation;
import com.softuni.residentevil.domain.etities.converters.MagnitudeAttributeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "viruses")
public final class Virus {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    // Name – Cannot be empty, should be between 3 and 10 symbols.
    @Column(nullable = false, unique = true, length = 10)
    private String name;

    // Description – Cannot be empty, should be between 5 and 100 symbols. Represented as Text in the database
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    // Side Effects – Should have a maximum of 50 symbols.
    @Column(length = 50)
    private String sideEffects;

    // Creator – Should be either Corp or corp.
    @Column(nullable = false, length = 5)
    private String creator;

    // Is Deadly – Boolean
    @Column(nullable = false)
    private Boolean isDeadly;

    // Is Curable – Boolean
    @Column(nullable = false)
    private Boolean isCurable;

    // Mutation – Cannot be null. Should hold one of the following values:
    // ZOMBIE, T_078_TYRANT, GIANT_SPIDER
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private Mutation mutation;

    // Turnover Rate – Number, between 0 and 100.
    @Column(nullable = false)
    private Integer turnoverRate;

    // THours Until Turn (to a mutation) – Number, between 1 and 12.
    @Column(nullable = false)
    private Integer hoursUntilMutation;

    // Low, Medium, High
    // Magnitude – Cannot be null. Should hold one of the following values:
    @Convert(converter = MagnitudeAttributeConverter.class)
    @Column(nullable = false, length = 6)
    private Magnitude magnitude;

    // Released On – Date, should be before the “today” date.
    @Column(nullable = false)
    private LocalDate releasedOn;

    // Capitals – A collection of Capitals.
    @ManyToMany
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = {@JoinColumn(name = "virus_id")},
            inverseJoinColumns = {@JoinColumn(name = "capital_id")})
    private Set<Capital> capitals = new HashSet<>();

    @Override
    public String toString() {
        return "Virus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", creator='" + creator + '\'' +
                ", isDeadly=" + isDeadly +
                ", isCurable=" + isCurable +
                ", mutation=" + mutation +
                ", turnoverRate=" + turnoverRate +
                ", hoursUntilMutation=" + hoursUntilMutation +
                ", magnitude=" + magnitude +
                ", releasedOn=" + releasedOn +
                ", capitals=" + capitals.stream().map(Capital::getName).collect(Collectors.joining(", ")) +
                '}';
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

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

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilMutation() {
        return this.hoursUntilMutation;
    }

    public void setHoursUntilMutation(Integer hoursUntilMutation) {
        this.hoursUntilMutation = hoursUntilMutation;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
