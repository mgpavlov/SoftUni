package org.softuni.resident_evil.service;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.models.service.CapitalServiceModel;
import org.softuni.resident_evil.domain.models.view.CapitalNameViewModel;
import org.softuni.resident_evil.repository.CapitalRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService{

    private final CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper, Validator validator) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public List<CapitalNameViewModel> capitalsNamesList(){
        return this.capitalRepository.getOrderedCapitals()
                .stream()
                .map(capital -> this.modelMapper.map(capital, CapitalNameViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CapitalServiceModel> orderedCapitals(){
        return this.capitalRepository.getOrderedCapitals()
                .stream()
                .map(capital -> this.modelMapper.map(capital, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }


}
