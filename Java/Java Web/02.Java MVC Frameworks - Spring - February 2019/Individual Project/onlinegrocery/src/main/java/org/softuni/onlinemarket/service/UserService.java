package org.softuni.onlinemarket.service;


import org.softuni.onlinemarket.domain.models.service.ProductServiceModel;
import org.softuni.onlinemarket.domain.models.service.UserServiceModel;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    void updateRole(String id, String role);

    UserServiceModel findUserByUserName(String name);
}
