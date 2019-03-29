package com.softuni.residentevil.services;

import com.softuni.residentevil.domain.etities.Capital;
import com.softuni.residentevil.domain.etities.Virus;
import com.softuni.residentevil.domain.models.binding.VirusAddEditBindingModel;
import com.softuni.residentevil.domain.models.view.VirusIdNameMagnitudeAndDateViewModel;
import com.softuni.residentevil.repositories.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public final class VirusServiceImpl extends BaseService implements VirusService {

    private final VirusRepository virusRepository;
    private final CapitalService capitalService;

    @Autowired
    public VirusServiceImpl(final Validator validator,
                            final ModelMapper modelMapper,
                            final VirusRepository virusRepository,
                            final CapitalService capitalService) {
        super(validator, modelMapper);
        this.virusRepository = virusRepository;
        this.capitalService = capitalService;
    }

    @Override
    protected <T> T mapDtoToEntity(final Object dto, final Class<T> entityClass) {
        final T virus = super.map(dto, entityClass);

        ((Virus) virus)
                .setCapitals(
                        ((VirusAddEditBindingModel) dto)
                                .getCapIds()
                                .stream()
                                .map(this.capitalService::getById)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toSet()));

        return virus;
    }

    @Override
    public boolean create(final Object dto) {
        return super.validateAndCreate(dto, Virus.class, this.virusRepository);
    }

    @Override
    public VirusAddEditBindingModel getById(final String id) {
        final Virus virus = this.virusRepository
                .findById(id)
                .orElse(null);

        if (virus != null) {
            final VirusAddEditBindingModel dto = super.map(virus, VirusAddEditBindingModel.class);
            dto.setCapIds(virus.getCapitals()
                    .stream()
                    .map(Capital::getId)
                    .collect(Collectors.toUnmodifiableList()));

            return dto;
        }

        return null;
    }

    @Override
    public void removeById(final String id) {
        this.virusRepository.deleteById(id);
    }

    @Override
    public List<VirusIdNameMagnitudeAndDateViewModel> getSimpleView() {
        return this.virusRepository
                .findAll()
                .stream()
                .map(virus -> super.map(virus, VirusIdNameMagnitudeAndDateViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean update(final VirusAddEditBindingModel dto) {
        final Virus virus = super.getEntityFromDto(dto, this.virusRepository);

        final Virus updated = this.mapDtoToEntity(dto, Virus.class);

        if (virus == null || updated == null) {
            return false;
        }

        updated.setReleasedOn(virus.getReleasedOn()); //Yet another level of protection (Date is not editable by requirement)

        return super.persist(updated, this.virusRepository);
    }
}
