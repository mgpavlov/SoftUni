package org.softuni.onlinegrocery.domain.models.view;

import org.softuni.onlinegrocery.domain.entities.enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderViewModel {

    private String id;
    private List<ProductDetailsViewModel> products;
    private UserProfileViewModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;
    private Status status;

    public OrderViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductDetailsViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailsViewModel> products) {
        this.products = products;
    }

    public UserProfileViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(UserProfileViewModel customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
