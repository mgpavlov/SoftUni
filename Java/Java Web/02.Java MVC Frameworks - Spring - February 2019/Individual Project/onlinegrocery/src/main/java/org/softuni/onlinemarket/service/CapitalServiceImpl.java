package org.softuni.onlinemarket.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.Capital;
import org.softuni.onlinemarket.domain.models.service.CapitalServiceModel;
import org.softuni.onlinemarket.repository.CapitalRepository;
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
