package exodia.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {


//    public Validator validate( ){
//        return Validation.buildDefaultValidatorFactory().getValidator().validate();
//    }
@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
