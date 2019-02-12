package metube.repository.interfaces;

import metube.domain.entities.Tube;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:25 ч.
 */
public interface TubeRepo extends GenericRepo<Tube, String> {

    Optional<Tube> findByName(String name);

}