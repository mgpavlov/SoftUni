package app.ccb.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name ="bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountWrapper {

    @XmlElement(name = "bank-account")
    private List<BankAccountDto> bankAccountDtos;

    public BankAccountWrapper() {
        this.bankAccountDtos = new ArrayList<>();
    }

    public List<BankAccountDto> getBankAccountDtos() {
        return this.bankAccountDtos;
    }

    public void setBankAccountDtos(List<BankAccountDto> bankAccountDtos) {
        this.bankAccountDtos = bankAccountDtos;
    }
}
