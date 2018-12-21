package app.ccb.services;

import app.ccb.constants.ResourcesPath;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository,
                           FileUtil fileUtil) {
        this.cardRepository = cardRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean cardsAreImported() {
        // TODO : Implement Me
        return true;
//        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() {
        return this.fileUtil.readFile(ResourcesPath.XML.CARDS);
    }

    @Override
    public String importCards() {
        // TODO : Implement Me
        return null;
    }
}
