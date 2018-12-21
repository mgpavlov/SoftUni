import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

import java.util.Date;

public class SalesDemo {

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setEmail("misho@java.com");
        customer.setName("Misho Mishov");
        customer.setCreditCardNumber("1234567");

        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setLocationName("warehous1");

        Product product = new Product();
        product.setName("jeans");
        product.setPrice(BigDecimal.valueOf(19.14));
        product.setQuantity(1D);

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(storeLocation);
        sale.setDate(new Date());


        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("sales");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(customer);
            entityManager.persist(product);
            entityManager.persist(storeLocation);
            entityManager.persist(sale);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
