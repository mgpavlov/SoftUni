package products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import products.dto.bindings.ProductImportDto;
import products.dto.utilities.CategoryIdDto;
import products.dto.utilities.UserIdDto;
import products.dto.views.product.ProductViewDto;
import products.entities.Category;
import products.entities.Product;
import products.entities.User;
import products.repositories.CategoryRepository;
import products.repositories.ProductRepository;
import products.repositories.UserRepository;
import products.utilities.MapperConverter;

import java.math.BigDecimal;
import java.util.*;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperConverter mapperConverter;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MapperConverter mapperConverter, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.mapperConverter = mapperConverter;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductViewDto> getProductsInRange(BigDecimal minPrice, BigDecimal maxPrice) throws InstantiationException, IllegalAccessException {
        List<Product> products = this.productRepository.getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(minPrice, maxPrice);
        return this.mapperConverter.convertList(products, ProductViewDto.class);
    }

    @Override
    public Product createOne(ProductImportDto productImportDto) {
        Product product = this.mapperConverter.convertOne(productImportDto, Product.class);
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> createMany(List<ProductImportDto> productImportDtos) throws IllegalAccessException, InstantiationException {
        List<Product> products = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        List<UserIdDto> userIdDtos = this.mapperConverter.convertList(users, UserIdDto.class);
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryIdDto> categoryIdDtos = this.mapperConverter.convertList(categories, CategoryIdDto.class);
        Random rnd = new Random();

        for (int i = 0; i < productImportDtos.size(); i++) {
            if (i % 10 != 0) {
                productImportDtos.get(i).setBuyer(userIdDtos.get(rnd.nextInt(userIdDtos.size())));

            }
            productImportDtos.get(i).setSeller(userIdDtos.get(rnd.nextInt(userIdDtos.size())));

            Set<CategoryIdDto> categoryIdDtoSet = new HashSet<>();
            for (int j = 0; j < rnd.nextInt(10); j++) {
                categoryIdDtoSet.add(categoryIdDtos.get(rnd.nextInt(categoryIdDtos.size())));
            }
            productImportDtos.get(i).setCategories(categoryIdDtoSet);

            products.add(this.createOne(productImportDtos.get(i)));
        }
        return products;
    }

    @Override
    public Product updateOne(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> updateMany(Iterable<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        for (Product product : products) {
            this.productRepository.save(product);
        }
        return (List<Product>) products;

    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByProduct(Product product) {
        this.productRepository.delete(product);
    }

}
