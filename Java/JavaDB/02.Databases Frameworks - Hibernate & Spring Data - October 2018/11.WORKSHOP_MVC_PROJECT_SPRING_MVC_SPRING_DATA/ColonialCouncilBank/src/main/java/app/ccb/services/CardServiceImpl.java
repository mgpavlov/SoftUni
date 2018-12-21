package app.ccb.services;

import app.ccb.domain.dtos.bankaccount.BankAccountImportRootDto;
import app.ccb.domain.dtos.card.CardImportDto;
import app.ccb.domain.dtos.card.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;

import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.CacheResponse;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final static String CARDS_XML_FILE_PATH = "C:\\Projects\\SoftUni\\Java\\JavaDB\\02.Databases Frameworks - Hibernate & Spring Data - October 2018\\ExamPreparation\\workshop1\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\cards.xml";
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, BankAccountRepository bankAccountRepository) {
        this.cardRepository = cardRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        String cardsFileContent = this.fileUtil.readFile(CARDS_XML_FILE_PATH);
        return cardsFileContent;
    }

    @Override
    public String importCards() throws JAXBException {
        StringBuilder importResult = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(CardImportRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        CardImportRootDto cardImportRootDto = (CardImportRootDto) unmarshaller
                .unmarshal(new File(CARDS_XML_FILE_PATH));
        for (CardImportDto cardImportDto : cardImportRootDto.getCardImportDtos()) {
            if (!validationUtil.isValid(cardImportDto)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            BankAccount bankAccountEntity = this.bankAccountRepository.getBankAccountByAccountNumber(cardImportDto.getAccountNumber()).orElse(null);
            if (bankAccountEntity == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Card cardEntity = modelMapper.map(cardImportDto, Card.class);
            cardEntity.setBankAccount(bankAccountEntity);

            importResult.append(String.format("Successfully imported Card - %s", cardEntity.getCardNumber())).append(System.lineSeparator());

            this.cardRepository.saveAndFlush(cardEntity);
        }

        return importResult.toString().trim();
    }
}
