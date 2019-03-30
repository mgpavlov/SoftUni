package org.softuni.cardealer.domain.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddCustomerBindingModel {
    private String name;

    private LocalDate birthDate;

    public AddCustomerBindingModel() {
    }

    @NotEmpty
    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
