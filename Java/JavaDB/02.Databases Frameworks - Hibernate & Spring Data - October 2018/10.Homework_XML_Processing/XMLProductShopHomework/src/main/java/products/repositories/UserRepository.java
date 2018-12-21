package products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import products.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.id, u.first_name, u.last_name, u.age\n" +
            "FROM users AS u\n" +
            "INNER JOIN products AS p\n" +
            "WHERE p.seller_id = u.id\n" +
            "AND p.buyer_id IS NOT NULL\n" +
            "GROUP BY u.id\n" +
            "ORDER BY u.last_name, u.first_name", nativeQuery = true)
    List<User> getUsersBySoldProductsIsNotNull();

    @Query(value = "SELECT u.*\n" +
            "FROM users AS u\n" +
            "INNER JOIN products AS p\n" +
            "ON u.id = p.seller_id\n" +
            "AND p.buyer_id IS NOT NULL\n" +
            "GROUP BY u.id\n" +
            "ORDER BY count(p.id) DESC;", nativeQuery = true)
    List<User> findUsers();
}
