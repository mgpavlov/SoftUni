package org.softuni.onlinegrocery.domain.models.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {

    private ProductDetailsViewModel product;
    private int quantity;
    private BigDecimal price;

    public ShoppingCartItem() {
    }

    public ProductDetailsViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductDetailsViewModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
