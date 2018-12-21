package mostwanted.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface RaceService {

    Boolean racesAreImported();

    String readRacesXmlFile() throws IOException;

    String importRaces() throws FileNotFoundException, UnsupportedEncodingException, JAXBException;
}
