package softuni.residentevil.domain.models.binding;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.residentevil.annotations.IsBefore;
import softuni.residentevil.domain.entities.Magnitude;
import softuni.residentevil.domain.entities.Mutation;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VirusBindingModel {
  private static final String INVALID_NAME_MSG = "Invalid name!";
  private static final String INVALID_DESC_MSG = "Description should be from 5 to 100 symbols long!";
  private static final String INVALID_SE_MSG = "Invalid side effect length! Maximum length is 50 symbols.";
  private static final String NO_SUCH_VALUE = "Invalid value.";
  private static final String HOURS_UNTIL_TURN_ERROR = "Value must be min 1 and max 12.";
  private static final String TURN_OVER_RATE_ERROR = "Turnover rate must be between 0 and 100.";
  private static final String CREATOR_ERROR = "Creator must be Corp or corp.";
  private static final String INVALID_SIZE_ERR = "You must select at least one capital.";

  private String id;
  private String name;
  private String description;
  private String sideEffects;
  private String creator;
  private boolean isDeadly;
  private boolean isCurable;
  private Mutation mutation;
  private Integer turnoverRate;
  private Integer hoursUntilTurn;
  private Magnitude magnitude;

  private LocalDate releasedOn;
  private List<String> capitals;

  public VirusBindingModel() {
    this.capitals = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Size(min = 3, max = 10, message = INVALID_NAME_MSG)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Size(min = 5, max = 100, message = INVALID_DESC_MSG)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Size(max = 50, message = INVALID_SE_MSG)
  public String getSideEffects() {
    return sideEffects;
  }

  public void setSideEffects(String sideEffects) {
    this.sideEffects = sideEffects;
  }

  @NotNull(message = NO_SUCH_VALUE)
  @Pattern(regexp = "^corp|Corp$", message = CREATOR_ERROR)
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public boolean isDeadly() {
    return isDeadly;
  }

  public void setDeadly(boolean deadly) {
    isDeadly = deadly;
  }

  public boolean isCurable() {
    return isCurable;
  }

  public void setCurable(boolean curable) {
    isCurable = curable;
  }

  @NotNull(message = NO_SUCH_VALUE)
  public Mutation getMutation() {
    return mutation;
  }

  public void setMutation(Mutation mutation) {
    this.mutation = mutation;
  }

  @NotNull
  @Range(min = 0, max = 100, message = TURN_OVER_RATE_ERROR)
  public Integer getTurnoverRate() {
    return turnoverRate;
  }

  public void setTurnoverRate(Integer turnoverRate) {
    this.turnoverRate = turnoverRate;
  }

  @NotNull
  @Range(min = 1, max = 12, message = HOURS_UNTIL_TURN_ERROR)
  public Integer getHoursUntilTurn() {
    return hoursUntilTurn;
  }

  public void setHoursUntilTurn(Integer hoursUntilTurn) {
    this.hoursUntilTurn = hoursUntilTurn;
  }

  @NotNull(message = NO_SUCH_VALUE)
  public Magnitude getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(Magnitude magnitude) {
    this.magnitude = magnitude;
  }

  @NotNull
  @IsBefore
  public LocalDate getReleasedOn() {
    return releasedOn;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setReleasedOn(LocalDate releasedOn) {
    this.releasedOn = releasedOn;
  }

  @NotNull
  @Size(min = 1, message = INVALID_SIZE_ERR)
  public List<String> getCapitals() {
    return capitals;
  }

  public void setCapitals(List<String> capitals) {
    this.capitals = capitals;
  }
}
