package softuni.residentevil.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.residentevil.domain.entities.Role;
import softuni.residentevil.domain.entities.User;
import softuni.residentevil.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {

  boolean register(UserServiceModel userServiceModel);

  UserServiceModel findById(String id);

  List<UserServiceModel> listAllUsers(String username);

  boolean update(UserServiceModel user);

  List<Role> listAllRoles();
}
