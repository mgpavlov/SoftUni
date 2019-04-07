package org.softuni.onlinemarket.repository;

import org.softuni.onlinemarket.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    @Query("" +
            "SELECT c FROM Capital c ORDER BY c.name")
    List<Capital> findAllOrOrderByName();

    Optional<Capital> findByName(String name);
}
