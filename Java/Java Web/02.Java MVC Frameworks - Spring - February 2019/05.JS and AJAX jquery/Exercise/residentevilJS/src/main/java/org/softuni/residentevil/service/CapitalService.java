package org.softuni.residentevil.service;

import org.softuni.residentevil.domain.models.service.CapitalServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CapitalService {
    List<CapitalServiceModel> findAllCapitals();

    CapitalServiceModel findCapitalByName(String name);
}
