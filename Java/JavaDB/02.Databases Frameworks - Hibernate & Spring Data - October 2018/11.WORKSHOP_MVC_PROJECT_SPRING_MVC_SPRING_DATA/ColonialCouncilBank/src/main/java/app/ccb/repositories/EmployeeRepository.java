package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    /*List<Employee> getEmployeesByClientsNotNullAndOrderByClientsDescAndIdAsc();*/

    @Query("SELECT e FROM app.ccb.domain.entities.Employee e WHERE e.clients is not empty order by size(e.clients)desc, e.id asc")
    List<Employee> getEmployees();

    @Query("" +
            "SELECT e " +
            "FROM app.ccb.domain.entities.Employee e " +
//            "JOIN e.clients " +
            "WHERE size(e.clients) > 0 " +
//            "GROUP BY e.id " +
            "ORDER BY size(e.clients) DESC, e.id "
    )
    List<Employee> extractTopEmployees();


}
