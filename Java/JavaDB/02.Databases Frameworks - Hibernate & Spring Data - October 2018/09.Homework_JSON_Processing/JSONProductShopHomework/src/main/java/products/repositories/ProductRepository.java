package products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal minPrice, BigDecimal maxPrice);


    List<Product> getProductsBySellerIdAndBuyerIsNotNull(long sellerId);
}
