package org.softuni.onlinegrocery.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.domain.models.view.ProductDetailsViewModel;
import org.softuni.onlinegrocery.domain.models.view.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingCartItem createShoppingCartItem(ProductDetailsViewModel product, int quantity) {
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItem;
    }
}
