package cars.repositories;

import cars.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT s.* FROM car_dealers_db.suppliers AS s WHERE s.is_importer = FALSE", nativeQuery = true)
    List<Supplier> getSuppliersByImporterIsFalse();
}
