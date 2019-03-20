package com.softuni.residentevil.repositories;

import com.softuni.residentevil.domain.etities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirusRepository extends JpaRepository<Virus, String> {

}
