package com.softuni.residentevil.services;

import com.softuni.residentevil.domain.etities.Capital;
import com.softuni.residentevil.domain.models.view.CapitalNameAndIdViewModel;
import com.softuni.residentevil.repositories.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class CapitalServiceImpl extends BaseService implements CapitalService {

    private final CapitalRepository capitalRepository;
    private List<CapitalNameAndIdViewModel> allCapitalSimpleViewCached;

    @Autowired
    public CapitalServiceImpl(final Validator validator,
                              final ModelMapper modelMapper,
                              final CapitalRepository capitalRepository) {
        super(validator, modelMapper);
        this.capitalRepository = capitalRepository;
    }


    @Override
    public boolean create(final Object dto) {
        if (super.validateAndCreate(dto, Capital.class, this.capitalRepository)) {
            allCapitalSimpleViewCached = this.getSimpleView();
            return true;
        }

        return false;
    }

    public List<CapitalNameAndIdViewModel> getSimpleView() {
        if (this.allCapitalSimpleViewCached == null) {
            this.allCapitalSimpleViewCached = this.capitalRepository
                    .findAll()
                    .stream()
                    .map(capital -> super.map(capital, CapitalNameAndIdViewModel.class))
                    .sorted(Comparator.comparing(CapitalNameAndIdViewModel::getName))
                    .collect(Collectors.toUnmodifiableList());
        }

        return this.allCapitalSimpleViewCached;
    }

    @Override
    public Capital getByName(final String name) {
        return this.capitalRepository
                .findByName(name);
    }

    @Override
    public Capital getById(final Long id) {
        return this.capitalRepository
                .findById(id)
                .orElse(null);
    }
}
