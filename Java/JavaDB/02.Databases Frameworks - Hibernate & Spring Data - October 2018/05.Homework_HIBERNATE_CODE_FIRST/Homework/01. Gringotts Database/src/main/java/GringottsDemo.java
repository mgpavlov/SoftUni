import entities.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class GringottsDemo {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gringotts");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            WizardDeposit deposit = new WizardDeposit();
            deposit.setFirstName("Mihail");
            deposit.setLastName("Pavlov");
            deposit.setAge(37);
            deposit.setDepositStartDate(new Date());

            entityManager.getTransaction().begin();

            entityManager.persist(deposit);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
