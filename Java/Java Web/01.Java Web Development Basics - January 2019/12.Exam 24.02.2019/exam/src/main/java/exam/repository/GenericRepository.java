package exam.repository;

import java.util.List;

public interface GenericRepository<E, ID> {

    E registerEntity(E entity);

    E updateEntity(E entity);

    boolean deleteEntity(ID id);

    List<E> findAll();

    E findById(ID id);

}
