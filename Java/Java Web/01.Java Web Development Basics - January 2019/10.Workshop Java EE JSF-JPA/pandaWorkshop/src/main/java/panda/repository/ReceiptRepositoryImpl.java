package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Receipt;
import panda.domain.entities.Status;
import panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {

    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Receipt> findAllReceiptsByRecipientUsername(String username) {
        this.entityManager.getTransaction().begin();
        List<Receipt> receipts = this.entityManager
                .createQuery("SELECT r FROM Receipt r WHERE r.recipient.username = :username", Receipt.class)
                .setParameter("username", username)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return receipts;
    }

    @Override
    public Receipt save(Receipt entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public Receipt findById(String id) {
        this.entityManager.getTransaction().begin();
        Receipt receipt = this.entityManager
                .createQuery("SELECT r FROM Receipt r WHERE r.id = :id", Receipt.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return receipt;
    }

    @Override
    public Long size() {
        return null;
    }
}
