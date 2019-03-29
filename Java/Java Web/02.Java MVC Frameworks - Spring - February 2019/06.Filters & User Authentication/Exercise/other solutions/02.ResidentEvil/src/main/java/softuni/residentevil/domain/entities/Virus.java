package softuni.residentevil.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "viruses")
public class Virus extends BaseEntity {
  private String name;
  private String description;
  private String sideEffects;
  private Creator creator;
  private boolean isDeadly;
  private boolean isCurable;
  private Mutation mutation;
  private Integer turnoverRate;
  private Integer hoursUntilTurn;
  private Magnitude magnitude;
  private LocalDate releasedOn;
  private List<Capital> capitals;

  public Virus() {
    this.capitals = new ArrayList<>();
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description", nullable = false, columnDefinition = "TEXT")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "side_effects")
  public String getSideEffects() {
    return sideEffects;
  }

  public void setSideEffects(String sideEffects) {
    this.sideEffects = sideEffects;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "creator")
  public Creator getCreator() {
    return creator;
  }

  public void setCreator(Creator creator) {
    this.creator = creator;
  }

  @Column(name = "is_deadly")
  public boolean isDeadly() {
    return isDeadly;
  }

  public void setDeadly(boolean deadly) {
    isDeadly = deadly;
  }

  @Column(name = "is_curable")
  public boolean isCurable() {
    return isCurable;
  }

  public void setCurable(boolean curable) {
    isCurable = curable;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "mutation", nullable = false)
  public Mutation getMutation() {
    return mutation;
  }

  public void setMutation(Mutation mutation) {
    this.mutation = mutation;
  }

  @Column(name = "turnover_rate")
  public Integer getTurnoverRate() {
    return turnoverRate;
  }

  public void setTurnoverRate(Integer turnoverRate) {
    this.turnoverRate = turnoverRate;
  }

  @Column(name = "hours_until_turn")
  public Integer getHoursUntilTurn() {
    return hoursUntilTurn;
  }

  public void setHoursUntilTurn(Integer hoursUntilTurn) {
    this.hoursUntilTurn = hoursUntilTurn;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "magnitude", nullable = false)
  public Magnitude getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(Magnitude magnitude) {
    this.magnitude = magnitude;
  }

  @Column(name = "released_on")
  public LocalDate getReleasedOn() {
    return releasedOn;
  }

  public void setReleasedOn(LocalDate releasedOn) {
    this.releasedOn = releasedOn;
  }

  @ManyToMany(targetEntity = Capital.class)
  @JoinTable(name = "viruses_capitals",
      joinColumns = @JoinColumn(name = "virus_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "capital_id",
          referencedColumnName = "id"))
  public List<Capital> getCapitals() {
    return capitals;
  }

  public void setCapitals(List<Capital> capitals) {
    this.capitals = capitals;
  }
}
