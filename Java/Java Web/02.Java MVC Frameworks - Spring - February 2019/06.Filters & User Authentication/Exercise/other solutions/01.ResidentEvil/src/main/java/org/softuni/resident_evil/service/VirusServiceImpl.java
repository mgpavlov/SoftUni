package org.softuni.resident_evil.service;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.softuni.resident_evil.domain.entities.Capital;
import org.softuni.resident_evil.domain.entities.Virus;
import org.softuni.resident_evil.domain.models.binding.VirusEditBindingModel;
import org.softuni.resident_evil.domain.models.service.VirusServiceModel;
import org.softuni.resident_evil.domain.models.view.VirusTableViewModel;
import org.softuni.resident_evil.repository.CapitalRepository;
import org.softuni.resident_evil.repository.VirusRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService{

    private final VirusRepository virusRepository;

    private final CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;


    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper,
                            Validator validator,
                            CapitalService capitalService,
                            CapitalRepository capitalRepository) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        this.capitalRepository = capitalRepository;
    }

    @Override
    public boolean createVirus(VirusServiceModel serviceModel){
        Virus virus = this.modelMapper.map(serviceModel, Virus.class);
        virus.setCapitals(this.capitalRepository.findCapitalsByIds(serviceModel.getCapitals()));
        try{
            this.virusRepository.saveAndFlush(virus);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<VirusTableViewModel> getAllViruses(){
        return this.virusRepository.findAll()
                .stream()
                .map(virus -> this.modelMapper.map(virus, VirusTableViewModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public VirusServiceModel getOneById(String id) {
        Virus virus = this.virusRepository
                .findById(id)
                .orElse(null);

        this.modelMapper.addConverter(new Converter<Capital, Long>() {
            public Long convert(MappingContext<Capital, Long> context) {
                return context.getSource().getId();
            }
        });
        return virus == null ? null
                : this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public boolean editVirus(VirusEditBindingModel editBindingModel) {
        Virus virus = this.virusRepository
                .findById(editBindingModel.getId())
                .orElse(null);

        Virus updated = this.modelMapper.map(editBindingModel, Virus.class);

        if (virus == null || updated == null) {
            return false;
        }
        updated.setId(virus.getId());
        updated.setReleasedOn(virus.getReleasedOn());
        updated.setCapitals(this.capitalRepository.findCapitalsByIds(editBindingModel.getCapitals()));

        try {
            this.virusRepository.saveAndFlush(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteVirus(String id){
        try {
            this.virusRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
