package app.ccb.services;

import app.ccb.constants.ResourcesPath;
import app.ccb.domain.dtos.BankAccountDto;
import app.ccb.domain.dtos.BankAccountWrapper;
import app.ccb.domain.entities.BankAccount;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  Gson gson,
                                  ModelMapper mapper,
                                  ValidationUtil validationUtil,
                                  XmlParser xmlParser,
                                  FileUtil fileUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        // TODO : Implement Me
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() {
        return this.fileUtil.readFile(ResourcesPath.XML.BANK_ACCOUNTS);
    }

    @Override
    public String importBankAccounts() {
        List<String> responseMessages = new ArrayList<>();
        try {
            BankAccountWrapper bankAccountWrapper = this.xmlParser.deserialize(this.readBankAccountsXmlFile(), BankAccountWrapper.class);
            List<BankAccount> successCreated = new ArrayList<>(bankAccountWrapper.getBankAccountDtos().size());
            for (BankAccountDto bankAccountDto : bankAccountWrapper.getBankAccountDtos()) {
                if (this.validationUtil.isValid(bankAccountDto)) {
                    BankAccount bankAccount = this.mapper.map(bankAccountDto, BankAccount.class);
                    successCreated.add(bankAccount);
                    responseMessages.add(String.format("Successfully imported %s – %s.", "Bank Account", bankAccountDto.getAccountNumber()));
                } else {
                    responseMessages.add("Error: Incorrect Data!");
                }
            }
            this.bankAccountRepository.saveAll(successCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessages.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
