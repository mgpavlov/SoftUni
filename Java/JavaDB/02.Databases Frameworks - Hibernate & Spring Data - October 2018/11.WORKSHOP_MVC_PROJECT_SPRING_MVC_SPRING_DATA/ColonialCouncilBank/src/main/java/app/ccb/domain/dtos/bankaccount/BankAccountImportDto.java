package app.ccb.domain.dtos.bankaccount;

import com.sun.xml.bind.AccessorFactory;

import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportDto {

    @XmlAttribute(name = "client")
    private String client;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "balance")
    private BigDecimal balance;

    public BankAccountImportDto() {
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @NotNull
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
