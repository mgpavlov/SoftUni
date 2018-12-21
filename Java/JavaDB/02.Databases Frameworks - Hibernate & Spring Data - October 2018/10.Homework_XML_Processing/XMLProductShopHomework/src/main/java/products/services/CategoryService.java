package products.services;

import products.dto.bindings.CategoryImportDto;
import products.dto.views.category.CategoryByProductsCountViewDto;
import products.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    List<CategoryByProductsCountViewDto> getCategoriesByProductsCount();

    Category createOne(CategoryImportDto categoryImportDto);

    List<Category> createMany(Iterable<CategoryImportDto> categoryDtos);

    Category updateOne(Category category);

    List<Category> updateMany(Iterable<Category> categorys);

    void deleteById(long id);

    void deleteByCategory(Category category);
}
