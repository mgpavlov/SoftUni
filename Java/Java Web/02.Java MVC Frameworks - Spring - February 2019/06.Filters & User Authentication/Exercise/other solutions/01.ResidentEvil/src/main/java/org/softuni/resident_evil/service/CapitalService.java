package org.softuni.resident_evil.service;

import org.softuni.resident_evil.domain.models.service.CapitalServiceModel;
import org.softuni.resident_evil.domain.models.view.CapitalNameViewModel;

import java.util.List;

public interface CapitalService {
    List<CapitalNameViewModel> capitalsNamesList();


    List<CapitalServiceModel> orderedCapitals();
}
