package org.softuni.resident_evil.service;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.entities.User;
import org.softuni.resident_evil.domain.entities.UserRole;
import org.softuni.resident_evil.domain.models.binding.UserEditBindingModel;
import org.softuni.resident_evil.domain.models.service.UserServiceModel;
import org.softuni.resident_evil.repository.UserRepository;
import org.softuni.resident_evil.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           Validator validator, BCryptPasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    private Set<UserRole> getRolesForRegistration() {
        Set<UserRole> roles = new HashSet<>();

        if (this.userRepository.count() == 0) {
            roles.add(this.userRoleRepository.getUserRoleByAuthority("ROLE_ADMIN"));
            roles.add(this.userRoleRepository.getUserRoleByAuthority("ROLE_MODERATOR"));
            roles.add(this.userRoleRepository.getUserRoleByAuthority("ROLE_USER"));
        } else {
            roles.add(this.userRoleRepository.getUserRoleByAuthority("ROLE_USER"));
        }

        return roles;
    }

    @Override
    public boolean saveUser(UserServiceModel serviceModel){
        if (this.validator.validate(serviceModel).size() > 0){
            return false;
        }
        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(serviceModel.getPassword()));
        user.setAuthorities(this.getRolesForRegistration());
        try{
            this.userRepository.saveAndFlush(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<UserServiceModel> getAllUsers(){
        List<User> allUsers = this.userRepository.findAll();
        return allUsers
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails result = this.userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        return result;
    }

    @Override
    public boolean editUser(UserEditBindingModel bindingModel)  {
        User user = (User) this.loadUserByUsername(bindingModel.getUsername());
        user.setAuthorities(bindingModel.getAuthorities());
        try{
            this.userRepository.saveAndFlush(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
