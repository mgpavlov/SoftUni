package products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import products.dto.bindings.UserImportDto;
import products.dto.views.product.ProductNamePriceViewDto;
import products.dto.views.product.ProductsNamePriceViewDto;
import products.dto.views.user.SummaryUsersSoldProductsViewDto;
import products.dto.views.user.UserNameSoldProductsViewDto;
import products.dto.views.user.UserSoldProductsViewDto;
import products.entities.Product;
import products.entities.User;
import products.repositories.ProductRepository;
import products.repositories.UserRepository;
import products.utilities.MapperConverter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, MapperConverter mapperConverter) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserNameSoldProductsViewDto> getUsersBySoldProducts() throws InstantiationException, IllegalAccessException {
        List<User> users = this.userRepository.getUsersBySoldProductsIsNotNull();
        return this.mapperConverter.convertList(users, UserNameSoldProductsViewDto.class);
    }

    @Override
    public SummaryUsersSoldProductsViewDto findUsers() throws InstantiationException, IllegalAccessException {
        List<User> users = this.userRepository.findUsers();
        List<UserSoldProductsViewDto> userSoldProductsViewDtos = this.mapperConverter.convertList(users, UserSoldProductsViewDto.class);
        for (UserSoldProductsViewDto userSoldProductsViewDto : userSoldProductsViewDtos) {
            List<Product> products = this.productRepository.getProductsBySellerIdAndBuyerIsNotNull(userSoldProductsViewDto.getId());
            List<ProductNamePriceViewDto> productNamePriceViewDtos = this.mapperConverter.convertList(products, ProductNamePriceViewDto.class);
            ProductsNamePriceViewDto productsNamePriceViewDtos = new ProductsNamePriceViewDto();
            productsNamePriceViewDtos.setProducts(new LinkedHashSet<>(productNamePriceViewDtos));
            userSoldProductsViewDto.setSoldProducts(productsNamePriceViewDtos);
        }
        SummaryUsersSoldProductsViewDto summary = new SummaryUsersSoldProductsViewDto();
        summary.setUsers(new LinkedHashSet<>(userSoldProductsViewDtos));
        return summary;
    }

    @Override
    public User createOne(UserImportDto userImportDto) {
        User user = this.mapperConverter.convertOne(userImportDto, User.class);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> createMany(Iterable<UserImportDto> userDtos) throws InstantiationException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        for (UserImportDto userImportDto : userDtos) {
            User user = this.createOne(userImportDto);
            users.add(user);
        }
        return users;
    }

    @Override
    public User updateOne(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> updateMany(Iterable<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }

        for (User user : users) {
            this.userRepository.save(user);
        }
        return (List<User>) users;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByUser(User user) {
        this.userRepository.delete(user);
    }

}
