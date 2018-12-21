package app.ccb.services;

import app.ccb.domain.dtos.bankaccount.BankAccountImportDto;
import app.ccb.domain.dtos.bankaccount.BankAccountImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final static String BANK_ACCOUNTS_XML_FILE_PATH = "C:\\Projects\\SoftUni\\Java\\JavaDB\\02.Databases Frameworks - Hibernate & Spring Data - October 2018\\ExamPreparation\\workshop1\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\bank-accounts.xml";
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, ClientRepository clientRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        String bankAccountsFileContent = this.fileUtil.readFile(BANK_ACCOUNTS_XML_FILE_PATH);
        return bankAccountsFileContent;
    }

    @Override
    public String importBankAccounts() throws JAXBException {
        StringBuilder importResult = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(BankAccountImportRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        BankAccountImportRootDto bankAccountImportRootDto = (BankAccountImportRootDto) unmarshaller
                .unmarshal(new File(BANK_ACCOUNTS_XML_FILE_PATH));

        for (BankAccountImportDto bankAccountImportDto : bankAccountImportRootDto.getBankAccountImportDtos()) {

            if (!validationUtil.isValid(bankAccountImportDto)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Client client = this.clientRepository.getByFullName(bankAccountImportDto.getClient()).orElse(null);

            if (client == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            BankAccount bankAccountEntity = modelMapper.map(bankAccountImportDto, BankAccount.class);
            bankAccountEntity.setClient(client);

            importResult.append(String.format("Successfully imported Bank Account - %s", bankAccountEntity.getAccountNumber())).append(System.lineSeparator());
            this.bankAccountRepository.saveAndFlush(bankAccountEntity);
        }

        return importResult.toString().trim();
    }
}
