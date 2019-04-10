package org.softuni.onlinegrocery.service;

import org.softuni.onlinegrocery.domain.models.view.ProductDetailsViewModel;
import org.softuni.onlinegrocery.domain.models.view.ShoppingCartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    ShoppingCartItem createShoppingCartItem(ProductDetailsViewModel product, int quantity);
}
