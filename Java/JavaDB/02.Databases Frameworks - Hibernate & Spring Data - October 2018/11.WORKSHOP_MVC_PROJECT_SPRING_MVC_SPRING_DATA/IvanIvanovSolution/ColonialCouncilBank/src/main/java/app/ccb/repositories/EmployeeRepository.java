package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findOneByFirstNameAndLastName(String firstName,String lastName);

    @Query("SELECT e from Employee as e WHERE e.clients.size >0 order by e.clients.size desc,e.id asc")
    List<Employee> extractDb();
}
