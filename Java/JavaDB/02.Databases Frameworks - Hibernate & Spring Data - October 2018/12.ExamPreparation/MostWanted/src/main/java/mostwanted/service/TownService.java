package mostwanted.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface TownService {

    Boolean townsAreImported();

    String readTownsJsonFile() throws IOException;

    String importTowns(String townsFileContent);

    String exportRacingTowns();
}
