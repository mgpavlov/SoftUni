package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystemapp.domain.entities.Album;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
