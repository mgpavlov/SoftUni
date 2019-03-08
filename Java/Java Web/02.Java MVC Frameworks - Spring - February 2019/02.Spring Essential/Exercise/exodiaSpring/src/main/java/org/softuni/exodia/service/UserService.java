package org.softuni.exodia.service;

import org.softuni.exodia.domain.model.service.UserServiceModel;

public interface UserService {
    boolean registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);
}
