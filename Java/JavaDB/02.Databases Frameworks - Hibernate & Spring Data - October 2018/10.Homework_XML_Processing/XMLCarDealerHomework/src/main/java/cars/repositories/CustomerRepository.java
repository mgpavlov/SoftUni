package cars.repositories;

import cars.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c.* FROM car_dealers_db.customers AS c ORDER BY c.birth_date ASC, c.is_young_driver ASC", nativeQuery = true)
    List<Customer> getCustomersByOrderByBirthDateAscYoungDriverAsc();

    @Query(value = "SELECT DISTINCT c.* FROM car_dealers_db.customers AS c INNER JOIN car_dealers_db.sales AS s ON c.id = s.customer_id", nativeQuery = true)
    List<Customer> getCustomersByBoughtCars();
}
