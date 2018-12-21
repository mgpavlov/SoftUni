package products.services;

import products.dto.bindings.ProductImportDto;
import products.dto.views.product.ProductViewDto;
import products.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    List<ProductViewDto> getProductsInRange(BigDecimal minPrice, BigDecimal maxPrice) throws InstantiationException, IllegalAccessException;

    Product createOne(ProductImportDto productImportDto);

    List<Product> createMany(List<ProductImportDto> productImportDtos) throws IllegalAccessException, InstantiationException;

    Product updateOne(Product product);

    List<Product> updateMany(Iterable<Product> products);

    void deleteById(long id);

    void deleteByProduct(Product product);
}
