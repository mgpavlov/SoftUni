package regapp.repository;

import regapp.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Employee> findAll() {
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM employees e ", Employee.class)
                .getResultList();


        this.entityManager.getTransaction().commit();

        return employees;
    }

    @Override
    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();
        Employee employee = this.entityManager
                .createQuery("SELECT e FROM employees e WHERE e.id = :id ", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM employees e WHERE e.id =: id")
                .setParameter("id", id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public BigDecimal getTotalMoneyNeeded() {

        this.entityManager.getTransaction().begin();
        BigDecimal result = this.entityManager.createQuery("SELECT sum (e.salary) FROM employees e", BigDecimal.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return result;
    }

    @Override
    public BigDecimal getCountOfEmployee() {
        this.entityManager.getTransaction().begin();
        Long count = this.entityManager.createQuery("SELECT count (e.id) FROM employees e", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return BigDecimal.valueOf(count);
    }

}
