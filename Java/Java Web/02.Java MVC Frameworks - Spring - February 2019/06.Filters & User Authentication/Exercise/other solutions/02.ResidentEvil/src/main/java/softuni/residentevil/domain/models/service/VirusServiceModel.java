package softuni.residentevil.domain.models.service;

import softuni.residentevil.domain.entities.Capital;
import softuni.residentevil.domain.entities.Creator;
import softuni.residentevil.domain.entities.Magnitude;
import softuni.residentevil.domain.entities.Mutation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VirusServiceModel {
  private String id;
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

  public VirusServiceModel() {
    this.capitals = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSideEffects() {
    return sideEffects;
  }

  public void setSideEffects(String sideEffects) {
    this.sideEffects = sideEffects;
  }

  public Creator getCreator() {
    return creator;
  }

  public void setCreator(Creator creator) {
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

  public Mutation getMutation() {
    return mutation;
  }

  public void setMutation(Mutation mutation) {
    this.mutation = mutation;
  }

  public Integer getTurnoverRate() {
    return turnoverRate;
  }

  public void setTurnoverRate(Integer turnoverRate) {
    this.turnoverRate = turnoverRate;
  }

  public Integer getHoursUntilTurn() {
    return hoursUntilTurn;
  }

  public void setHoursUntilTurn(Integer hoursUntilTurn) {
    this.hoursUntilTurn = hoursUntilTurn;
  }

  public Magnitude getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(Magnitude magnitude) {
    this.magnitude = magnitude;
  }

  public LocalDate getReleasedOn() {
    return releasedOn;
  }

  public void setReleasedOn(LocalDate releasedOn) {
    this.releasedOn = releasedOn;
  }

  public List<Capital> getCapitals() {
    return capitals;
  }

  public void setCapitals(List<Capital> capitals) {
    this.capitals = capitals;
  }
}
