package com.softuni.residentevil.services;

import com.softuni.residentevil.domain.api.Identifiable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

abstract class BaseService {

    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    protected BaseService(final Validator validator,
                          final ModelMapper modelMapper) {
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    final boolean isValid(final Object t) {
        return t != null && this.validator.validate(t).isEmpty();
    }

    final <T> Set<ConstraintViolation<T>> validate(final T t) {
        return this.validator.validate(t);
    }

    final <T> T map(final Object source, final Class<T> clazz) {
        return this.modelMapper.map(source, clazz);
    }

    <T> T mapDtoToEntity(final Object dto, final Class<T> entityClass) {
        return this.modelMapper.map(dto, entityClass);
    }

    final <ENTITY> boolean validateAndCreate(final Object dto,
                                             final Class<ENTITY> entityClass,
                                             final JpaRepository<ENTITY, ?> repository) {
        if (!this.isValid(dto)) {
            return false; // TODO - proper error handling
        }

        final ENTITY entity = this.mapDtoToEntity(dto, entityClass);

        return this.persist(entity, repository);
    }

    final boolean persist(final Object entity,
                          final JpaRepository repository) {
        try {
            repository.saveAndFlush(entity);
        } catch (Throwable e) {
            return false;   // TODO - proper error handling
        }

        return true;
    }

    final <ENTITY, ID, DTO extends Identifiable<ID>> ENTITY getEntityFromDto(
            final DTO dto,
            final JpaRepository<ENTITY, ID> repository) {

        if (dto != null && dto.getId() != null && this.isValid(dto)) {
            return repository
                    .findById(dto.getId())
                    .orElse(null);
        }

        return null;
    }
}
