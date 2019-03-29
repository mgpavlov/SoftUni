package com.example.residentevil.service.api;

import com.example.residentevil.domain.models.service.CapitalsServiceModel;

import java.util.List;

public interface CapitalsService {

    List<CapitalsServiceModel> findAllSortedByName();
}
