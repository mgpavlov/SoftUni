package com.example.residentevil.service.api;

import com.example.residentevil.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    void updateRole(String id, String role);
}
