package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder result = new StringBuilder();

        List<Category> categories = this.categoryRepository.exportCategoriesCountItems();

        categories.stream().sorted((a,b)->{
            return Double.compare(
            b.getItems().stream().mapToDouble(i-> Double.parseDouble(i.getPrice().toString())).sum(),
            a.getItems().stream().mapToDouble(i-> Double.parseDouble(i.getPrice().toString())).sum()
            );
        }).collect(Collectors.toList()).forEach(category->{
            result.append(String.format("Category:%s", category.getName())).append(System.lineSeparator());

            for (Item item : category.getItems()) {
                result.append(String.format("---Item Name:%s", item.getName())).append(System.lineSeparator());
                result.append(String.format("---Item Price:%s", item.getPrice())).append(System.lineSeparator());
                result.append(System.lineSeparator());
            }
        });

        return result.toString().trim();
    }
}
