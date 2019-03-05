package exam.service;

import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    boolean userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);


}
