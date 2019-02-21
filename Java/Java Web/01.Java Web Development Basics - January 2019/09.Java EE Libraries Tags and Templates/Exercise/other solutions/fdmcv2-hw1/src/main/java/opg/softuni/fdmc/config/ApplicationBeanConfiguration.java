package opg.softuni.fdmc.config;

import opg.softuni.fdmc.constants.StringConstants;
import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {
    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory(StringConstants.PERSISTANCE_UNIT_NAME)
                .createEntityManager();
    }
}
