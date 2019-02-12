package metube.repository;

import metube.domain.entities.Tube;
import metube.repository.interfaces.TubeRepo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import static metube.constants.Constants.PARAMETER_ID;
import static metube.constants.Constants.PARAMETER_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:29 ч.
 */
public class TubeRepoImpl implements TubeRepo {

    private EntityManager entityManager;

    @Inject
    public TubeRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube entity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Optional<Tube> findById(String id) {
        return getProductByParameter(id, PARAMETER_ID);
    }

    @Override
    public List<Tube> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Tube> criteriaQuery = criteriaBuilder
                .createQuery(Tube.class);
        Root<Tube> root = criteriaQuery.from(Tube.class);

        criteriaQuery.select(root);

        return this.entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public Optional<Tube> findByName(String name) {
        return getProductByParameter(name, PARAMETER_NAME);
    }

    private Optional<Tube> getProductByParameter(String name, String parameterName) {
        CriteriaBuilder criteriaBuilder = this.entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Tube> criteriaQuery = criteriaBuilder
                .createQuery(Tube.class);
        Root<Tube> root = criteriaQuery.from(Tube.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(parameterName), name));

        try {
            return Optional.ofNullable(this.entityManager
                    .createQuery(criteriaQuery)
                    .getSingleResult());

        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }
}