package opg.softuni.fdmc.repositories;

import opg.softuni.fdmc.domain.entities.BaseEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.function.Function;

public abstract class GenericRepository {

    private final EntityManager em;
    private EntityTransaction transaction;

    @Inject
    protected GenericRepository(EntityManager em) {
        this.em = em;
    }

    private void openTransaction() {
        if(this.transaction == null || !this.transaction.isActive()) {
            this.transaction = em.getTransaction();
            this.transaction.begin();
        }
    }

    private void closeTransaction() {
        if(this.transaction != null && this.transaction.isActive()) {
            this.transaction.commit();
            this.transaction = null;
        }
    }

    protected Object execute(Function<EntityManager, Object> consumer) {
        this.openTransaction();
        Object result = consumer.apply(this.em);
        this.closeTransaction();
        return result;
    }

    // TODO: 2/13/2019 make generic
    protected BaseEntity getEntityByStringValue(String columnName, String value) {
        CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();

        CriteriaQuery<BaseEntity> query =
                em.getCriteriaBuilder()
                        .createQuery(BaseEntity.class);

        Root<BaseEntity> root = query.from(BaseEntity.class);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);
        query.select(root).where(criteriaBuilder.equal(root.get(columnName), parameter));

        return (BaseEntity) execute((em) ->{
            TypedQuery<BaseEntity> tubeQuery = this.em.createQuery(query);
            tubeQuery.setParameter(parameter, value);
            return tubeQuery.getSingleResult();
        });

    }
}
