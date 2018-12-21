package mostwanted.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface CarService {

    Boolean carsAreImported();

    String readCarsJsonFile() throws IOException;

    String importCars(String carsFileContent);
}
