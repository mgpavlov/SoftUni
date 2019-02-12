package metube.repository.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:24 ч.
 */
public interface GenericRepo<E, K> {

    E save(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

}
