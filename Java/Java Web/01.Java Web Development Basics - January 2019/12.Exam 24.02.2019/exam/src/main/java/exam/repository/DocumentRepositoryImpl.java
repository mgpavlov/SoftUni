package exam.repository;

import exam.domain.entities.Document;
import exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {

    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Document registerEntity(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Document updateEntity(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            Document updatedDocument = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedDocument;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public boolean deleteEntity(String id) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.createQuery("DELETE FROM Document d WHERE d.id = :id")
                    .setParameter("id", id).executeUpdate();
            this.entityManager.getTransaction().commit();

            return true;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return false;
        }
    }



    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();
        try {
            List<Document> documents = this.entityManager
                    .createQuery("SELECT d FROM Document d ", Document.class)
                    .getResultList();
            this.entityManager.getTransaction().commit();

            return documents;
        }catch (Exception e){
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Document findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager
                    .createQuery("SELECT d FROM Document d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return document;
        }catch (Exception e){
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Document findByTitle(String title) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager
                    .createQuery("SELECT d FROM Document d WHERE d.title = :title", Document.class)
                    .setParameter("title", title)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return document;
        }catch (Exception e){
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }
}
