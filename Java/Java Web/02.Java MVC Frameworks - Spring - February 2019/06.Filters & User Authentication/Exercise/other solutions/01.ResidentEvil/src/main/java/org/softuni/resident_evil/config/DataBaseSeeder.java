package org.softuni.resident_evil.config;

import org.softuni.resident_evil.domain.entities.UserRole;
import org.softuni.resident_evil.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseSeeder {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataBaseSeeder(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.userRoleRepository.findAll().isEmpty()) {
            UserRole userRole = new UserRole();
            userRole.setAuthority("ROLE_USER");

            UserRole adminRole = new UserRole();
            adminRole.setAuthority("ROLE_ADMIN");

            UserRole moderatorRole = new UserRole();
            moderatorRole.setAuthority("ROLE_MODERATOR");

            this.userRoleRepository.save(userRole);
            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(moderatorRole);
        }
    }
}
