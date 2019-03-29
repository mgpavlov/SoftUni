package softuni.residentevil.service;

import softuni.residentevil.domain.models.binding.VirusBindingModel;
import softuni.residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {
  List<VirusServiceModel> findAllViruses();

  VirusServiceModel save(VirusBindingModel bindingModel);

  VirusServiceModel update(VirusBindingModel bindingModel);

  VirusServiceModel findById(String id);

  void deleteById(String id);
}
