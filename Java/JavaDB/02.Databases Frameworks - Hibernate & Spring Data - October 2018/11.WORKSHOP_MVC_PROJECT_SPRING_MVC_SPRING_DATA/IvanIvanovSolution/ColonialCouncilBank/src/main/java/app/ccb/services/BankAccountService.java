package app.ccb.services;

public interface BankAccountService {

    Boolean bankAccountsAreImported();

    String readBankAccountsXmlFile();

    String importBankAccounts();
}
