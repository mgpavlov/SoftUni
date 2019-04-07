package org.softuni.onlinemarket.service;

import org.softuni.onlinemarket.domain.models.service.CapitalServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CapitalService {
    List<CapitalServiceModel> findAllCapitals();

    CapitalServiceModel findCapitalByName(String name);
}
