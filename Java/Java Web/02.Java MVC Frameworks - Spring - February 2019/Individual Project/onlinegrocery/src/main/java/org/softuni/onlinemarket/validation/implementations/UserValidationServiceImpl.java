package org.softuni.onlinemarket.validation.implementations;

import org.softuni.onlinemarket.domain.models.service.UserServiceModel;
import org.softuni.onlinemarket.validation.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
