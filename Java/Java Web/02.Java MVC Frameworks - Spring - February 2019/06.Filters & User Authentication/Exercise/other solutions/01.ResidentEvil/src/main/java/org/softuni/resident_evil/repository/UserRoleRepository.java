package org.softuni.resident_evil.repository;

import org.softuni.resident_evil.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    UserRole getUserRoleByAuthority(String authority);

}
