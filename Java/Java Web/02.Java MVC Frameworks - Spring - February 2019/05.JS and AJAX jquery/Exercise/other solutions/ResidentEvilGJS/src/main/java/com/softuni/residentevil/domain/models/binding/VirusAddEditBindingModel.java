package com.softuni.residentevil.domain.models.binding;

import com.softuni.residentevil.domain.api.Identifiable;
import com.softuni.residentevil.domain.enums.Magnitude;
import com.softuni.residentevil.domain.enums.Mutation;
import com.softuni.residentevil.domain.models.view.CapitalNameAndIdViewModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"capIds", "allCapitals", "allMutations", "allMagnitudes"})
public final class VirusAddEditBindingModel implements Identifiable<String> {

    private static final String VIRUS_NAME_LENGTH = "{virus.name.length}";
    private static final String VIRUS_DESCRIPTION_LENGTH = "{virus.description.length}";
    private static final String VIRUS_SIDE_EFFECTS_LENGTH = "{virus.side-effects.length}";
    private static final String VIRUS_CREATOR_TEXT = "{virus.creator.text}";
    private static final String VIRUS_DEADLY_NULL = "{virus.deadly.null}";
    private static final String VIRUS_CURABLE_NULL = "{virus.curable.null}";
    private static final String VIRUS_MUTATION_NULL = "{virus.mutation.null}";
    private static final String VIRUS_TURNOVER_RATE_NULL = "{virus.turnover-rate.null}";
    private static final String VIRUS_TURNOVER_RATE_RANGE = "{virus.turnover-rate.range}";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_NULL = "{virus.hours-until-mutation.null}";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_RANGE = "{virus.hours-until-mutation.range}";
    private static final String VIRUS_MAGNITUDE_NULL = "{virus.magnitude.null}";
    private static final String VIRUS_RELEASED_ON_DATE_NULL = "{virus.released-on-date.null}";
    private static final String VIRUS_RELEASED_ON_DATE_INVALID = "{virus.released-on-date.invalid}";
    private static final String VIRUS_CAPITALS_EMPTY = "{virus.capitals.empty}";

    private String id;

    // Name – Cannot be empty, should be between 3 and 10 symbols.
    @Length(min = 3, max = 10, message = VIRUS_NAME_LENGTH)
    private String name;

    // Description – Cannot be empty, should be between 5 and 100 symbols. Represented as Text in the database
    @Length(min = 5, max = 100, message = VIRUS_DESCRIPTION_LENGTH)
    private String description;

    // Side Effects – Should have a maximum of 50 symbols.
    @Length(max = 50, message = VIRUS_SIDE_EFFECTS_LENGTH)
    private String sideEffects;

    // Creator – Should be either Corp or corp.
    @Pattern(regexp = "^Corp$|^corp\\.$", message = VIRUS_CREATOR_TEXT)
    private String creator;

    // Is Deadly – Boolean
    @NotNull(message = VIRUS_DEADLY_NULL)
    private Boolean isDeadly;

    // Is Curable – Boolean
    @NotNull(message = VIRUS_CURABLE_NULL)
    private Boolean isCurable;

    // Mutation – Cannot be null. Should hold one of the following values:
    // ZOMBIE, T_078_TYRANT, GIANT_SPIDER
    @NotNull(message = VIRUS_MUTATION_NULL)
    private Mutation mutation;

    // Turnover Rate – Number, between 0 and 100.
    @NotNull(message = VIRUS_TURNOVER_RATE_NULL)
    @Range(min = 0, max = 100, message = VIRUS_TURNOVER_RATE_RANGE)
    private Integer turnoverRate;

    // THours Until Turn (to a mutation) – Number, between 1 and 12.
    @NotNull(message = VIRUS_HOURS_UNTIL_MUTATION_NULL)
    @Range(min = 1, max = 12, message = VIRUS_HOURS_UNTIL_MUTATION_RANGE)
    private Integer hoursUntilMutation;

    // Magnitude – Cannot be null. Should hold one of the following values:
    // Low, Medium, High
    @NotNull(message = VIRUS_MAGNITUDE_NULL)
    private Magnitude magnitude;

    // Released On – Date, should be before the “today” date.
    @NotNull(message = VIRUS_RELEASED_ON_DATE_NULL)
    @Past(message = VIRUS_RELEASED_ON_DATE_INVALID)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedOn;

    // Capitals – A collection of Capitals.
    @NotEmpty(message = VIRUS_CAPITALS_EMPTY)
    private List<Long> capIds = new ArrayList<>();

    // Transfer data required for form creation
    private List<CapitalNameAndIdViewModel> allCapitals = new ArrayList<>();
    private List<String> allMutations = new ArrayList<>();
    private List<String> allMagnitudes = new ArrayList<>();

    @Override
    public String toString() {
        return "VirusAddEditBindingModel{" +
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
                ", capIds=" + capIds +
                ", allCapitals=" + allCapitals.size() +
                ", allMutations=" + allMutations.size() +
                ", allMagnitudes=" + allMagnitudes.size() +
                '}';
    }

    @Override
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

    public List<Long> getCapIds() {
        return this.capIds;
    }

    public void setCapIds(List<Long> capIds) {
        this.capIds = capIds;
    }

    public List<CapitalNameAndIdViewModel> getAllCapitals() {
        return this.allCapitals;
    }

    public void setAllCapitals(List<CapitalNameAndIdViewModel> allCapitals) {
        this.allCapitals = allCapitals;
    }

    public List<String> getAllMutations() {
        return this.allMutations;
    }

    public void setAllMutations(List<String> allMutations) {
        this.allMutations = allMutations;
    }

    public List<String> getAllMagnitudes() {
        return this.allMagnitudes;
    }

    public void setAllMagnitudes(List<String> allMagnitudes) {
        this.allMagnitudes = allMagnitudes;
    }
}
