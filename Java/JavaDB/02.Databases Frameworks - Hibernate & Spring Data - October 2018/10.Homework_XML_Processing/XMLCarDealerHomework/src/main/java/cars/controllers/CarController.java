package cars.controllers;

import cars.dto.bindings.CarImportDto;
import cars.dto.bindings.CarsImportXmlDto;
import cars.dto.views.car.*;
import cars.serializers.Serializer;
import cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class CarController {
    private static final String CARS_JSON_INPUT_PATH = "/json/input/cars.json";
    private static final String CARS_XML_INPUT_PATH = "/xml/input/cars.xml";
    private static final String TOYOTA_CARS_JSON_OUTPUT_PATH = "/src/main/resources/json/output/toyota-cars.json";
    private static final String TOYOTA_CARS_XML_OUTPUT_PATH = "src/main/resources/xml/output/toyota-cars.xml";
    private static final String CARS_AND_PARTS_JSON_OUTPUT_PATH = "/src/main/resources/json/output/cars-and-parts.json";
    private static final String CARS_AND_PARTS_XML_OUTPUT_PATH = "src/main/resources/xml/output/cars-and-parts.xml";

    private final CarService carService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public CarController(CarService carService, @Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer) {
        this.carService = carService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importCarsFromJson() {
        CarImportDto[] carImportDtos = this.jsonSerializer.deserialize(CarImportDto[].class, CARS_JSON_INPUT_PATH);
        this.carService.createMany(Arrays.asList(carImportDtos));
    }

    public void importCarsFromXml() {
        CarsImportXmlDto carsImportXmlDto = this.xmlSerializer.deserialize(CarsImportXmlDto.class, CARS_XML_INPUT_PATH);
        this.carService.createMany(carsImportXmlDto.getCarImportDtos());
    }

    public void exportToyotaCarsToJson() {
        List<CarToyotaViewDto> carToyotaViewDtos = this.carService.getCarsFromMakeToyota("Toyota");
        this.jsonSerializer.serialize(carToyotaViewDtos, TOYOTA_CARS_JSON_OUTPUT_PATH);
    }

    public void exportToyotaCarsToXml() {
        List<CarToyotaViewDto> carToyotaViewDtos = this.carService.getCarsFromMakeToyota("Toyota");
        CarsToyotaXmlViewDto carsToyotaXmlViewDto = new CarsToyotaXmlViewDto();
        carsToyotaXmlViewDto.setCarToyotaViewDtos(carToyotaViewDtos);
        this.xmlSerializer.serialize(carsToyotaXmlViewDto, TOYOTA_CARS_XML_OUTPUT_PATH);
    }

    public void exportCarsWithPartsToJson() {
        List<CarWithPartsJsonViewDto> carWithPartsViewDtos = this.carService.getCarsWithPartsJson();
        this.jsonSerializer.serialize(carWithPartsViewDtos, CARS_AND_PARTS_JSON_OUTPUT_PATH);
    }

    public void exportCarsWithPartsToXml() {
        List<CarWithPartsXmlViewDto> carsWithPartsXml = this.carService.getCarsWithPartsXml();
        CarsWithPartsXmlViewDto carsWithPartsXmlViewDto = new CarsWithPartsXmlViewDto();
        carsWithPartsXmlViewDto.setCarWithPartsViewDtos(carsWithPartsXml);
        this.xmlSerializer.serialize(carsWithPartsXmlViewDto, CARS_AND_PARTS_XML_OUTPUT_PATH);
    }
}
