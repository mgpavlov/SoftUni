package com.example.residentevil.service.impl;

import com.example.residentevil.domain.entities.Virus;
import com.example.residentevil.domain.models.service.VirusServiceModel;
import com.example.residentevil.repository.VirusRepository;
import com.example.residentevil.service.api.VirusService;
import com.example.residentevil.util.validators.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository,
                            ModelMapper modelMapper,
                            Validator validator) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public VirusServiceModel spreadVirus(VirusServiceModel virusServiceModel) {
        if (!this.validator.isValid(virusServiceModel)) {
            throw new IllegalArgumentException("Invalid model!");
        }

        Virus virusEntity = this.modelMapper.map(virusServiceModel, Virus.class);
        Virus savedVirus = this.virusRepository.saveAndFlush(virusEntity);

        return this.modelMapper.map(savedVirus, VirusServiceModel.class);
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(virusEntity -> this.modelMapper.map(virusEntity, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteVirusById(String id) {
        try {
            this.virusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public VirusServiceModel findById(String id) {
        Virus virusEntity = this.virusRepository.findById(id).orElse(null);

        return virusEntity == null ? null
                : this.modelMapper.map(virusEntity, VirusServiceModel.class);
    }

    @Override
    public void editVirus(VirusServiceModel virusServiceModel) {
        if (!this.validator.isValid(virusServiceModel)) {
            throw new IllegalArgumentException("Invalid model!");
        }

        Virus virusToUpdate = this.virusRepository.getOne(virusServiceModel.getId());
        this.prepareForUpdate(virusServiceModel, virusToUpdate);
        this.virusRepository.save(virusToUpdate);
    }

    private void prepareForUpdate(VirusServiceModel virusServiceModel, Virus virusToUpdate) {
        virusToUpdate.setName(virusServiceModel.getName());
        virusToUpdate.setCreator(virusServiceModel.getCreator());
        virusToUpdate.setCurable(virusServiceModel.getCurable());
        virusToUpdate.setDeadly(virusServiceModel.getDeadly());
        virusToUpdate.setDescription(virusServiceModel.getDescription());
        virusToUpdate.setHoursUtilTurn(virusServiceModel.getHoursUtilTurn());
        virusToUpdate.setMagnitude(virusServiceModel.getMagnitude());
        virusToUpdate.setMutation(virusServiceModel.getMutation());
        virusToUpdate.setSideEffects(virusServiceModel.getSideEffects());
        virusToUpdate.setTurnoverRate(virusServiceModel.getTurnoverRate());
    }

//    private void prepareForUpdate(VirusServiceModel virusServiceModel, Virus virusToUpdate) {
//
//        Class<? extends VirusServiceModel> aClass = virusServiceModel.getClass();
//        Field[] declaredFields = aClass.getDeclaredFields();
//
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//
//            if (declaredField.getName().equals("id")) {
//                continue;
//            }
//
//            Class<? extends Virus> clazz = virusToUpdate.getClass();
//            try {
//                String fieldName = declaredField.getName();
//                Field field = clazz.getDeclaredField(fieldName);
//                field.setAccessible(true);
//                field.set(virusToUpdate, declaredField.get(virusServiceModel));
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new IllegalStateException(e);
//            }
//        }
//    }
}
