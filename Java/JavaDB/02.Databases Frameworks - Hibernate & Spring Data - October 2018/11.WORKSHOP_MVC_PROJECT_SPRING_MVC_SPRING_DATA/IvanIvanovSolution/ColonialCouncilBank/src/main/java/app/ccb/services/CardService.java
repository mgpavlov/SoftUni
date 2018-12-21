package app.ccb.services;

public interface CardService {

    Boolean cardsAreImported();

    String readCardsXmlFile();

    String importCards();
}
