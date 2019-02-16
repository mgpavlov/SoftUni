package fdmcapp.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {

    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("fdmc")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
