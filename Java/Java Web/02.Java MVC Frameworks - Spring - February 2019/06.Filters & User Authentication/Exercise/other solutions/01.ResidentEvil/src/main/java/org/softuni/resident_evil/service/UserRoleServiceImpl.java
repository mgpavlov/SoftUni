package org.softuni.resident_evil.service;

import org.softuni.resident_evil.domain.entities.UserRole;
import org.softuni.resident_evil.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserRole> getRoles(){
        return this.roleRepository.findAll();

    }
}
