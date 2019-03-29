package org.softuni.resident_evil.domain.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class VirusAddBindingModel extends VirusBindingModel {

    private LocalDate releasedOn;

    public VirusAddBindingModel() {
        this.setCapitals(new LinkedHashSet<>());
    }
    @Past(message = "Invalid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }


}
