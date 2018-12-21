package products.services;

import products.dto.bindings.UserImportDto;
import products.dto.views.user.SummaryUsersSoldProductsViewDto;
import products.dto.views.user.UserNameSoldProductsViewDto;
import products.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    List<UserNameSoldProductsViewDto> getUsersBySoldProducts() throws InstantiationException, IllegalAccessException;

    SummaryUsersSoldProductsViewDto findUsers() throws InstantiationException, IllegalAccessException;

    User createOne(UserImportDto userImportDto);

    List<User> createMany(Iterable<UserImportDto> userDtos) throws InstantiationException, IllegalAccessException;

    User updateOne(User user);

    List<User> updateMany(Iterable<User> users);

    void deleteById(long id);

    void deleteByUser(User user);
}
