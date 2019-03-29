package org.softuni.resident_evil.domain.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class VirusDeleteBindingModel extends VirusBindingModel {

    private LocalDate releasedOn;

    public VirusDeleteBindingModel() {
        this.setCapitals(new LinkedHashSet<>());
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }


}
