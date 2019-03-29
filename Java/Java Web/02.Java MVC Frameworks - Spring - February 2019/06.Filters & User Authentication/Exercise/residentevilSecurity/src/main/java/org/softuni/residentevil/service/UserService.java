package org.softuni.residentevil.service;


import org.softuni.residentevil.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    void updateRole(String id, String role);
}
