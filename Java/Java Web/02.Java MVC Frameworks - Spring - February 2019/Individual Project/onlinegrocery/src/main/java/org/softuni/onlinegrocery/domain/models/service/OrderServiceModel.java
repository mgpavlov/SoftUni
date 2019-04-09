package org.softuni.onlinegrocery.domain.models.service;

import org.softuni.onlinegrocery.domain.entities.enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceModel extends BaseServiceModel {
    private List<ProductServiceModel> products;
    private UserServiceModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime issuedOn;
    private LocalDateTime statusDate;
    private String shippingAddress;
    private Status status;

    public OrderServiceModel() {
    }

    public List<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    public UserServiceModel getCustomer() {
        return customer;
    }

    public void setCustomer(UserServiceModel customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public LocalDateTime getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(LocalDateTime statusDate) {
        this.statusDate = statusDate;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
