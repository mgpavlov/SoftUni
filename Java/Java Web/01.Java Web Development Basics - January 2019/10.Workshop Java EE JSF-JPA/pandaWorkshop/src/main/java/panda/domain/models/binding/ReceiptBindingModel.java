package panda.domain.models.binding;

import panda.domain.entities.Package;
import panda.domain.entities.Receipt;
import panda.domain.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptBindingModel {

    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Package aPackage;

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

    public Package getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
