package org.softuni.onlinegrocery.domain.models.binding;

import org.softuni.onlinegrocery.domain.entities.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProductAddBindingModel {
    private static final String VIRUS_NAME_LENGTH = "Virus name cannot be empty, should be between 3 and 10 symbols!";


    private String name;
    private String description;
    private BigDecimal price;
    private MultipartFile image;
    private List<Category> categories;

    public ProductAddBindingModel() {
    }
    @NotNull
    @Size(min = 3, max = 10, message = VIRUS_NAME_LENGTH)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "not empty")
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotEmpty
    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
