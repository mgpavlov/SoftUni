package mostwanted.repository;

import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository  extends JpaRepository<Car, Integer> {
    Optional<Car> findByBrandAndAndModelAndRacer_Name(String brand, String model, String racerName);
    Optional<Car> findById(Integer id);
}
