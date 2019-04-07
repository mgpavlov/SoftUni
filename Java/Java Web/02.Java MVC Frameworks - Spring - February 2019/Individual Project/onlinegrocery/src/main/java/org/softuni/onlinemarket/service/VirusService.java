package org.softuni.onlinemarket.service;

import org.softuni.onlinemarket.domain.models.service.VirusServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VirusService {
    List<VirusServiceModel> findAllViruses();

    VirusServiceModel registerVirus(VirusServiceModel virusService);

    VirusServiceModel findVirusById(String id);

    boolean deleteVirus(String id);

    boolean update(VirusServiceModel virusServiceModel);
}
