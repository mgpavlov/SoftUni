package org.softuni.resident_evil.repository;

import org.softuni.resident_evil.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {

    @Query("" +
            "select c from Capital c order by c.name")
    List<Capital> getOrderedCapitals();

    @Query("" +
            "select c from Capital c where c.id in :capitals")
    Set<Capital> findCapitalsByIds(@Param("capitals") Set<Long> capitals);
}
