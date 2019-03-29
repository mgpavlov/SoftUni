package com.example.residentevil.service.impl;

import com.example.residentevil.domain.entities.Capitals;
import com.example.residentevil.domain.models.service.CapitalsServiceModel;
import com.example.residentevil.repository.CapitalsRepository;
import com.example.residentevil.service.api.CapitalsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalsServiceImpl implements CapitalsService {

    private final CapitalsRepository capitalsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalsServiceImpl(CapitalsRepository capitalsRepository,
                               ModelMapper modelMapper) {
        this.capitalsRepository = capitalsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CapitalsServiceModel> findAllSortedByName() {
        List<Capitals> capitals = this.capitalsRepository.findAllOrderByName();

        return capitals.stream()
                .map(c -> this.modelMapper.map(c, CapitalsServiceModel.class))
                .collect(Collectors.toList());
    }
}
