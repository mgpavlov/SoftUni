package softuni.residentevil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.residentevil.domain.entities.Virus;

@Repository
public interface VirusRepository extends JpaRepository<Virus, String> {
}
