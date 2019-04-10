package org.softuni.onlinegrocery.domain.models.binding;


import org.softuni.onlinegrocery.domain.entities.Order;
import org.softuni.onlinegrocery.domain.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptBindingModel {

    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Order order;

    public ReceiptBindingModel() {
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDateTime getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
