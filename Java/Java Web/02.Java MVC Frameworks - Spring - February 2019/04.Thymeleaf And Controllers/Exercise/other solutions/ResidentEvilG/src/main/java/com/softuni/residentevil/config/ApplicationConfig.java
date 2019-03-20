package com.softuni.residentevil.config;

import com.softuni.residentevil.utils.MessageWrapper;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationConfig {

    private static final String LANGUAGES_VALIDATION_MESSAGES = "languages/validation";

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator(LANGUAGES_VALIDATION_MESSAGES)
                        )
                )
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public MessageWrapper messageWrapper(final MessageSource messageSource) {
        return new MessageWrapper(messageSource);
    }
}
