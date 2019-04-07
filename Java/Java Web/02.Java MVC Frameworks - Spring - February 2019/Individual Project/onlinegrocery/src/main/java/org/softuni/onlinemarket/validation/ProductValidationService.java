package org.softuni.onlinemarket.validation;

import org.softuni.onlinemarket.domain.entities.Product;
import org.softuni.onlinemarket.domain.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValid(Product product);

    boolean isValid(ProductServiceModel product);
}
