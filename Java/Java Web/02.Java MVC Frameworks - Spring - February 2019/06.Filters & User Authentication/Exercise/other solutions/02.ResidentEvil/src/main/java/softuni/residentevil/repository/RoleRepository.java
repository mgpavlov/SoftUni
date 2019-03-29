package softuni.residentevil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.residentevil.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Role findByAuthority(String authority);
}
