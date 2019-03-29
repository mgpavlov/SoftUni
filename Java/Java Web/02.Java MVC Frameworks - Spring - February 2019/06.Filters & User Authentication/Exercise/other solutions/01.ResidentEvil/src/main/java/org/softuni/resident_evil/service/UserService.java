package org.softuni.resident_evil.service;

import org.softuni.resident_evil.domain.models.binding.UserEditBindingModel;
import org.softuni.resident_evil.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    boolean saveUser(UserServiceModel serviceModel);

    List<UserServiceModel> getAllUsers();

    boolean editUser(UserEditBindingModel bindingModel);
}
