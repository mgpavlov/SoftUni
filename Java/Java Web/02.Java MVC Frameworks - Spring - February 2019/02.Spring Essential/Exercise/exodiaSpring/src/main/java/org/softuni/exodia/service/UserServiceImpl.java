package org.softuni.exodia.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softuni.exodia.domain.entity.User;
import org.softuni.exodia.domain.model.service.UserServiceModel;
import org.softuni.exodia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        try {
            this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        UserServiceModel user = this.modelMapper
                .map(this.userRepository.findByUsername(userServiceModel.getUsername()), UserServiceModel.class);
        return user != null && user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()));
    }
}
