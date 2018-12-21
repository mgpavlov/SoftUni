package products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import products.dto.bindings.CategoryImportDto;
import products.dto.views.category.CategoryByProductsCountViewDto;
import products.entities.Category;
import products.repositories.CategoryRepository;
import products.utilities.MapperConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperConverter mapperConverter) {
        this.categoryRepository = categoryRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<CategoryByProductsCountViewDto> getCategoriesByProductsCount() {
        List<Object> categories = this.categoryRepository.getCategoriesByProductsCount();
        List<CategoryByProductsCountViewDto> categoryByProductsCountViewDtos = new ArrayList<>();
        for (Object category : categories) {
            CategoryByProductsCountViewDto categoryByProductsCountViewDto = new CategoryByProductsCountViewDto();
            Object[] categoryArrary = (Object[]) category;
            categoryByProductsCountViewDto.setCategory((String) categoryArrary[0]);
            categoryByProductsCountViewDto.setCount((BigInteger) categoryArrary[1]);
            categoryByProductsCountViewDto.setAveragePrice((BigDecimal) categoryArrary[2]);
            categoryByProductsCountViewDto.setTotalRevenue((BigDecimal) categoryArrary[3]);
            categoryByProductsCountViewDtos.add(categoryByProductsCountViewDto);
        }
        return categoryByProductsCountViewDtos;
    }

    @Override
    public Category createOne(CategoryImportDto categoryImportDto) {
        Category category = this.mapperConverter.convertOne(categoryImportDto, Category.class);
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> createMany(Iterable<CategoryImportDto> categoryDtos) {
        List<Category> categories = new ArrayList<>();
        for (CategoryImportDto categoryImportDto : categoryDtos) {
            categories.add(this.createOne(categoryImportDto));
        }
        return categories;
    }

    @Override
    public Category updateOne(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> updateMany(Iterable<Category> categorys) {
        if (categorys == null) {
            return new ArrayList<>();
        }

        for (Category category : categorys) {
            this.categoryRepository.save(category);
        }
        return (List<Category>) categorys;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByCategory(Category category) {
        this.categoryRepository.delete(category);
    }

}
