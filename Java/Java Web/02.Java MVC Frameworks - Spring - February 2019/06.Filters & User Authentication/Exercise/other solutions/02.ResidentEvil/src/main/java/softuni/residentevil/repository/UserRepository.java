package softuni.residentevil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.residentevil.domain.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM softuni.residentevil.domain.entities.User u WHERE u.username NOT LIKE :username")
  List<User> listAllUsersExceptAdmin(@Param(value = "username")String username);
}
