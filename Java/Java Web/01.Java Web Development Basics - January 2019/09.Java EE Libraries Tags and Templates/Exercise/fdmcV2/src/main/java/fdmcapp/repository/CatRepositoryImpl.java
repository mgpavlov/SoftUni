package fdmcapp.repository;

import fdmcapp.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Cat> findAll() {
        this.entityManager.getTransaction().begin();
        List<Cat> cats = this.entityManager
                .createQuery("SELECT c FROM cats c ", Cat.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return cats;
    }

    @Override
    public Cat findById(String id) {
        this.entityManager.getTransaction().begin();
        Cat cat = this.entityManager
                .createQuery("SELECT c FROM cats c WHERE c.id = :id ", Cat.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return cat;
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM cats c WHERE c.id =: id")
                .setParameter("id", id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public BigDecimal getCountOfCat() {
        this.entityManager.getTransaction().begin();
        Long count = this.entityManager.createQuery("SELECT count (c.id) FROM cats c", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return BigDecimal.valueOf(count);
    }
}
