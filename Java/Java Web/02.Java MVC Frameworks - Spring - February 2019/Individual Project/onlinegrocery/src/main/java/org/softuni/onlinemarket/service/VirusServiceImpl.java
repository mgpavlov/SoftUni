package org.softuni.onlinemarket.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.Virus;
import org.softuni.onlinemarket.domain.models.service.VirusServiceModel;
import org.softuni.onlinemarket.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService{
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(v->this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public VirusServiceModel registerVirus(VirusServiceModel virusService) {
        Virus virus = this.modelMapper.map(virusService, Virus.class);
        virus = this.virusRepository.saveAndFlush(virus);
        if (virus == null) {
            return null;
        }
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public VirusServiceModel findVirusById(String id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        if (virus == null) {
            return null;
        }
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public boolean deleteVirus(String id) {
        try {
            this.virusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(VirusServiceModel virusServiceModel) {
        Virus virus = this.virusRepository.findById(virusServiceModel.getId()).orElse(null);

        final Virus updated = this.modelMapper.map(virusServiceModel, Virus.class);

        if (virus == null || updated == null) {
            return false;
        }

        updated.setReleasedOn(virus.getReleasedOn());

        try {
            this.virusRepository.saveAndFlush(updated);
        }catch (Throwable e){
            return false;
        }
        return true;
    }

}
