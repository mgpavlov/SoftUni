package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() != 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        String carsFileContent = this.fileUtil.readFile(Constants.CARS_JSON_FILE_PATH);
        return carsFileContent;
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder importResult = new StringBuilder();
        CarImportDto[] carImportDtos = this.gson.fromJson(carsFileContent, CarImportDto[].class);

        for (CarImportDto carImportDto : carImportDtos) {
            Racer racer = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);
            if (!this.validationUtil.isValid(carImportDto) || racer == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Car carEntity = this.modelMapper.map(carImportDto, Car.class);
            carEntity.setRacer(racer);

            this.carRepository.saveAndFlush(carEntity);

            String carInfo = String.format("%s %s @ %d", carEntity.getBrand(), carEntity.getModel(), carEntity.getYearOfProduction());
            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, carEntity.getClass().getSimpleName(), carInfo)).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
