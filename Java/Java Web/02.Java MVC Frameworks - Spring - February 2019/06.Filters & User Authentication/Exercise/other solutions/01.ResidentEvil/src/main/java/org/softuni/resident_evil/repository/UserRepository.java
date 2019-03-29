package org.softuni.resident_evil.repository;

import org.softuni.resident_evil.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> getUserByUsername(String username);

}
