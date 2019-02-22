package exam.service;

import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    boolean userRegister(UserServiceModel userServiceModel);

    boolean userUpdate(UserServiceModel userServiceModel);

    boolean userDelete(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel findUserById(String id);

    List<UserServiceModel> findAllUsers();
}
