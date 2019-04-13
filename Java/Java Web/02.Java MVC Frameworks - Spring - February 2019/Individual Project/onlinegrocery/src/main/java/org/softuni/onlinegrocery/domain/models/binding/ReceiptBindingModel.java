/*
package org.softuni.onlinegrocery.domain.models.binding;


import org.softuni.onlinegrocery.domain.entities.Order;
import org.softuni.onlinegrocery.domain.entities.User;
import org.softuni.onlinegrocery.util.constants.AppConstants;
import org.softuni.onlinegrocery.util.constants.ValidationErrorMessages;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptBindingModel {

    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Order order;

    public ReceiptBindingModel() {
    }
    @NotNull(message = ValidationErrorMessages.EVENT_FEE_EMPTY_FIELD_ERROR_MSG)
    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @NotNull(message = ValidationErrorMessages.EVENT_DATE_EMPTY_FIELD_ERROR_MSG)
    //@DateTimeFormat(pattern = AppConstants.DATE_PATTERN)
    public LocalDateTime getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    @NotNull(message = ValidationErrorMessages.EVENT_DATE_EMPTY_FIELD_ERROR_MSG)
    public User getRecipient() {
        return this.recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @NotNull(message = ValidationErrorMessages.EVENT_DATE_EMPTY_FIELD_ERROR_MSG)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
*/
