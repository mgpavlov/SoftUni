package alararestaurant.repository;
import alararestaurant.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>  {

    @Query("" +
            "SELECT o " +
            "FROM alararestaurant.domain.entities.Order o " +
            "JOIN alararestaurant.domain.entities.Employee e " +
            "ON o.employee = e.id " +
            "JOIN alararestaurant.domain.entities.Position p " +
            "ON e.position = p.id " +
            "WHERE p.name = 'Burger Flipper' " +
            "ORDER BY e.name ASC , o.id ASC "
    )
    List<Order> exportOrdersByBurgerFlippers();






}
