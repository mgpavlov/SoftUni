package softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.residentevil.domain.entities.Capital;
import softuni.residentevil.domain.entities.Creator;
import softuni.residentevil.domain.entities.Virus;
import softuni.residentevil.domain.models.binding.VirusBindingModel;
import softuni.residentevil.domain.models.service.VirusServiceModel;
import softuni.residentevil.repository.CapitalRepository;
import softuni.residentevil.repository.VirusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {
  private final VirusRepository virusRepository;
  private final CapitalRepository capitalRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper) {
    this.virusRepository = virusRepository;
    this.capitalRepository = capitalRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<VirusServiceModel> findAllViruses() {
    return this.virusRepository.findAll()
        .stream()
        .map(v->this.modelMapper.map(v, VirusServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public VirusServiceModel save(VirusBindingModel bindingModel) {
    VirusServiceModel virusServiceModel = getVirusServiceModel(bindingModel);
    try {
      Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
      virus = this.virusRepository
          .saveAndFlush(virus);
      return this.modelMapper.map(virus, VirusServiceModel.class);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public VirusServiceModel update(VirusBindingModel bindingModel) {
    VirusServiceModel virusServiceModel = getVirusServiceModel(bindingModel);

    try{
      Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
      this.virusRepository.save(virus);
      return this.modelMapper.map(virus, VirusServiceModel.class);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public VirusServiceModel findById(String id) {
    Virus virus = this.virusRepository.findById(id).orElse(null);
    if (virus == null){
      return null;
    }
    return this.modelMapper.map(virus, VirusServiceModel.class);
  }

  @Override
  public void deleteById(String id) {
   this.virusRepository.deleteById(id);
  }

  private VirusServiceModel getVirusServiceModel(VirusBindingModel bindingModel) {
    List<Capital> capitals = new ArrayList<>();
    for (String capital : bindingModel.getCapitals()) {
      Capital capitalEntity = this.capitalRepository.findById(capital).orElse(null);
      if (capitalEntity != null){
        capitals.add(capitalEntity);
      }
    }
    VirusServiceModel virusServiceModel = this.modelMapper
        .map(bindingModel, VirusServiceModel.class);
    virusServiceModel.setCreator(Creator.valueOf(bindingModel.getCreator()));
    virusServiceModel.setCapitals(capitals);
    return virusServiceModel;
  }
}
