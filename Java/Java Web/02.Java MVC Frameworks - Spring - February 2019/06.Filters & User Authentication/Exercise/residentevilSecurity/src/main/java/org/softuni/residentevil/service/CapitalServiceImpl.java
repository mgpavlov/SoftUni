package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Capital;
import org.softuni.residentevil.domain.models.service.CapitalServiceModel;
import org.softuni.residentevil.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return this.capitalRepository.findAllOrOrderByName()
                .stream()
                .map(c->this.modelMapper.map(c, CapitalServiceModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CapitalServiceModel findCapitalByName(String name) {
        Capital capital = this.capitalRepository.findByName(name).orElse(null);
        if (capital == null) {
            return null;
        }
        return this.modelMapper.map(capital, CapitalServiceModel.class);
    }
}
