package softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.residentevil.domain.models.service.CapitalServiceModel;
import softuni.residentevil.repository.CapitalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {
  private final CapitalRepository capitalRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
    this.capitalRepository = capitalRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<CapitalServiceModel> findAllCapitals() {
    return this.capitalRepository.findAllOrderByName()
        .stream()
        .map(c->this.modelMapper.map(c, CapitalServiceModel.class))
        .collect(Collectors.toList());
  }

}
