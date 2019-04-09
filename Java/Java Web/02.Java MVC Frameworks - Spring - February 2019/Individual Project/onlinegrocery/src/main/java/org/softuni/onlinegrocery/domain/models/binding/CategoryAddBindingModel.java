package org.softuni.onlinegrocery.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryAddBindingModel {

    private String name;

    public CategoryAddBindingModel() {
    }

    @NotEmpty(message = "not empty")
    @NotNull(message = "not null")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
