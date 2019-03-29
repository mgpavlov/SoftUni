package com.softuni.residentevil.domain.models.view;

import com.softuni.residentevil.domain.api.Identifiable;
import com.softuni.residentevil.domain.enums.Magnitude;
import lombok.Data;

import java.time.LocalDate;

@Data
public final class VirusIdNameMagnitudeAndDateViewModel implements Identifiable<String> {

    private String id;

    private String name;

    private Magnitude magnitude;

    private LocalDate releasedOn;

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
}
