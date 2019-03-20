package org.softuni.residentevil.domain.models.binding;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.softuni.residentevil.domain.entities.Capital;
import org.softuni.residentevil.domain.entities.Creator;
import org.softuni.residentevil.domain.entities.Magnitude;
import org.softuni.residentevil.domain.entities.Mutation;
import org.softuni.residentevil.domain.models.view.CapitalViewModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class VirusAddBindingModel {
    private static final String VIRUS_NAME_LENGTH = "Virus name cannot be empty, should be between 3 and 10 symbols!";
    private static final String VIRUS_DESCRIPTION_LENGTH = "Virus description cannot be empty, should be between 5 and 100 symbols!";
    private static final String VIRUS_SIDE_EFFECTS_LENGTH = "Virus side effects should have a maximum of 50 symbols!";
    private static final String VIRUS_CREATOR_TEXT = "Virus Creator should be either Corp or corp!";
    private static final String VIRUS_DEADLY_NULL = "Deadly property cannot be null!";
    private static final String VIRUS_CURABLE_NULL = "Curable property cannot be null!";
    private static final String VIRUS_MUTATION_NULL = "Mutation cannot be null!";
    private static final String VIRUS_TURNOVER_RATE_NULL = "Turnover rate cannot be null!";
    private static final String VIRUS_TURNOVER_RATE_RANGE = "Turnover rate should be between 0 and 100!";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_NULL = "Hours until mutation cannot be null!";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_RANGE = "Hours until mutation should be between 1 and 12!";
    private static final String VIRUS_MAGNITUDE_NULL = "Magnitude cannot be null!";
    private static final String VIRUS_RELEASED_ON_DATE_NULL = "Release date cannot be null!";
    private static final String VIRUS_RELEASED_ON_DATE_INVALID = "Release date should be in the past!";
    private static final String VIRUS_CAPITALS_EMPTY = "You must select capitals!";

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


    @NotNull
    @Size(min = 3, max = 10, message = VIRUS_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 100, message = VIRUS_DESCRIPTION_LENGTH)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Size(max = 50, message = VIRUS_SIDE_EFFECTS_LENGTH)
    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @NotNull(message = VIRUS_DEADLY_NULL)
    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    @NotNull(message = VIRUS_CURABLE_NULL)
    public boolean isCurable() {
        return this.isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    @Enumerated(EnumType.STRING)
    @NotNull(message = VIRUS_CREATOR_TEXT)
    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @NotNull(message = VIRUS_MUTATION_NULL)
    @Enumerated(EnumType.STRING)
    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = VIRUS_MAGNITUDE_NULL)
    @Enumerated(EnumType.STRING)
    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @NotNull(message = VIRUS_TURNOVER_RATE_NULL)
    @Range(min = 0, max = 100, message = VIRUS_TURNOVER_RATE_RANGE)
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @NotNull(message = VIRUS_HOURS_UNTIL_MUTATION_NULL)
    @Range(min = 1, max = 12, message = VIRUS_HOURS_UNTIL_MUTATION_RANGE)
    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @NotNull(message = VIRUS_RELEASED_ON_DATE_NULL)
    @Past(message = VIRUS_RELEASED_ON_DATE_INVALID)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @NotEmpty(message = VIRUS_CAPITALS_EMPTY)
    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
