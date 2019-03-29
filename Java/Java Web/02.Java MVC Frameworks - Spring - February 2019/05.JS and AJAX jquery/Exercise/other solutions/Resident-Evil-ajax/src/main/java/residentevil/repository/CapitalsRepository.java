package residentevil.repository;

import residentevil.domain.entities.Capitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalsRepository extends JpaRepository<Capitals, String> {

    @Query("SELECT c FROM Capitals AS c ORDER BY c.name")
    List<Capitals> findAllOrderByName();
}
