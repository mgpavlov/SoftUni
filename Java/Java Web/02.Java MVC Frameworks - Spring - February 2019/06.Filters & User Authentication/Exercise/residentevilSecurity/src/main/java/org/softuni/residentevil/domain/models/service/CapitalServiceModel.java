package org.softuni.residentevil.domain.models.service;

import javax.validation.constraints.NotNull;

public class CapitalServiceModel {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;

    @NotNull
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @NotNull
    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
