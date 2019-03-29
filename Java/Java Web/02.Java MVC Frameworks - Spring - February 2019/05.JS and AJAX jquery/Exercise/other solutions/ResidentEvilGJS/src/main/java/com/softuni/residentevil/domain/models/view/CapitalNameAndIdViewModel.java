package com.softuni.residentevil.domain.models.view;

import com.softuni.residentevil.domain.api.Identifiable;
import lombok.Data;

@Data
public final class CapitalNameAndIdViewModel implements Identifiable<Long> {

    private Long id;

    private String name;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
