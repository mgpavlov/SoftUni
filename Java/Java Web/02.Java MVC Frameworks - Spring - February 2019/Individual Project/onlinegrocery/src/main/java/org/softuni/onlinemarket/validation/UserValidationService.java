package org.softuni.onlinemarket.validation;

import org.softuni.onlinemarket.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
