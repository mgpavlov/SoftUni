package org.softuni.onlinegrocery.validation.category;

import org.softuni.onlinegrocery.domain.models.binding.CategoryAddBindingModel;
import org.softuni.onlinegrocery.repository.CategoryRepository;
import org.softuni.onlinegrocery.validation.ValidationConstants;
import org.softuni.onlinegrocery.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class CategoryEditValidator implements org.springframework.validation.Validator {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryEditValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryAddBindingModel categoryEditBindingModel = (CategoryAddBindingModel) o;

        //Category category = this.categoryRepository.findByName(categoryEditBindingModel.getName()).orElse(null);

        if (categoryEditBindingModel.getName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

        if (this.categoryRepository.findByName(categoryEditBindingModel.getName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryEditBindingModel.getName()),
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryEditBindingModel.getName())
            );
        }
    }
}
