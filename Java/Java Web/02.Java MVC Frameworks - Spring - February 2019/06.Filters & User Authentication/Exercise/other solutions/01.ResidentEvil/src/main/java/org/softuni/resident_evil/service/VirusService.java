package org.softuni.resident_evil.service;

import org.softuni.resident_evil.domain.models.binding.VirusEditBindingModel;
import org.softuni.resident_evil.domain.models.service.VirusServiceModel;
import org.softuni.resident_evil.domain.models.view.VirusTableViewModel;

import java.util.List;

public interface VirusService {
    boolean createVirus(VirusServiceModel serviceModel);

    List<VirusTableViewModel> getAllViruses();

    VirusServiceModel getOneById(String id);

    boolean editVirus(VirusEditBindingModel editBindingModel);

    boolean deleteVirus(String id);
}
