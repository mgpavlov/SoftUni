package app.ccb.domain.dtos.bankaccount;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportRootDto {

    @XmlElement(name = "bank-account")
    private BankAccountImportDto[] bankAccountImportDtos;

    public BankAccountImportRootDto() {
    }

    public BankAccountImportDto[] getBankAccountImportDtos() {
        return this.bankAccountImportDtos;
    }

    public void setBankAccountImportDtos(BankAccountImportDto[] bankAccountImportDtos) {
        this.bankAccountImportDtos = bankAccountImportDtos;
    }
}
