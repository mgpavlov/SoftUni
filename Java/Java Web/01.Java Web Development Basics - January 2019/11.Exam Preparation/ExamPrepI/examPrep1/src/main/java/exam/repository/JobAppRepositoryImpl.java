package exam.repository;

import exam.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobAppRepositoryImpl implements JobAppRepository {

    private final EntityManager entityManager;

    @Inject
    public JobAppRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication registerEntity(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public JobApplication updateEntity(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        try {
            JobApplication updatedJobApp = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedJobApp;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public boolean deleteEntity(String id) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.createQuery("DELETE FROM JobApplication u WHERE u.id = :id")
                    .setParameter("id", id).executeUpdate();
            this.entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return false;
        }
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();
        try {
            List<JobApplication> jobs = this.entityManager
                    .createQuery("SELECT u FROM JobApplication u ", JobApplication.class)
                    .getResultList();
            this.entityManager.getTransaction().commit();

            return jobs;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public JobApplication findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            JobApplication user = this.entityManager
                    .createQuery("SELECT u FROM JobApplication u WHERE u.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public JobApplication findBySector(String sector) {
        this.entityManager.getTransaction().begin();
        try {
            JobApplication user = this.entityManager
                    .createQuery("SELECT u FROM JobApplication u WHERE u.sector = :sector", JobApplication.class)
                    .setParameter("sector", sector)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Long countOfJobs() {
        this.entityManager.getTransaction().begin();
        try {
            Long size = this.entityManager
                    .createQuery("SELECT count(u) FROM JobApplication u ", Long.class)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return size;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }
}
