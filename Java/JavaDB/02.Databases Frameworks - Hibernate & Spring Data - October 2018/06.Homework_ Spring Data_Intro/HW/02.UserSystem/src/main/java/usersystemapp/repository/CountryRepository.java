package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystemapp.domain.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
