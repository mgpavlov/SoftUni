package org.softuni.residentevil.repository;

import org.softuni.residentevil.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);
}
