package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTests {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private CarRepository carRepository;
    private CarService carService;
    private CarServiceModel carServiceModel;
    private ModelMapper modelMapper;
    private List<PartServiceModel> parts;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.carRepository, this.modelMapper);
        PartServiceModel first = new PartServiceModel() {{
            setName("part1");
            setPrice(new BigDecimal("33.33"));
        }};
        PartServiceModel second = new PartServiceModel() {{
            setName("part2");
            setPrice(new BigDecimal("44.44"));
        }};
        this.partRepository.saveAndFlush(this.modelMapper.map(first, Part.class));
        this.partRepository.saveAndFlush(this.modelMapper.map(second, Part.class));
        this.parts = partRepository.findAll().stream()
                .map(part -> this.modelMapper.map(part, PartServiceModel.class))
                .collect(Collectors.toList());
        this.carServiceModel = new CarServiceModel() {{
            setMake("BMW");
            setModel("7");
            setTravelledDistance(666000L);
            setParts(parts);
        }};
    }

    //Tests with correct data:
    @Test
    public void carService_saveCarWithCorrectFieldsValues_returnsCorrectCarServiceModel() {
        CarServiceModel expected = this.carService.saveCar(this.carServiceModel);
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);
        Assert.assertEquals("Save doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Save doesn't work!", expected.getMake(), actual.getMake());
        Assert.assertEquals("Save doesn't work!", expected.getModel(), actual.getModel());
        Assert.assertEquals("Save doesn't work!", expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals("Save doesn't work!", expected.getParts().get(0).getId(), actual.getParts().get(0).getId());
    }

    @Test
    public void carService_editCarWithCorrectFieldsValues_returnsCarServiceModel() {
        this.carRepository.saveAndFlush(this.modelMapper.map(this.carServiceModel, Car.class));
        CarServiceModel edited = new CarServiceModel() {{
            setId(carRepository.findAll().get(0).getId());
            setMake("Lada");
            setModel("500");
            setTravelledDistance(999000L);
            setParts(parts);
        }};
        CarServiceModel expected = this.carService.editCar(edited);
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);
        Assert.assertEquals("Edit doesn't work", expected.getId(), actual.getId());
        Assert.assertEquals("Edit doesn't work", expected.getMake(), actual.getMake());
        Assert.assertEquals("Edit doesn't work", expected.getModel(), actual.getModel());
        Assert.assertEquals("Edit doesn't work", expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals("Edit doesn't work", expected.getParts().get(0).getId(), actual.getParts().get(0).getId());
    }

    @Test
    public void carService_deleteCarWithCorrectId_returnsCarServiceModel() {
        this.carRepository.saveAndFlush(this.modelMapper.map(this.carServiceModel, Car.class));
        String id = this.carRepository.findAll().get(0).getId();
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);
        CarServiceModel expected = this.carService.deleteCar(id);
        Assert.assertEquals("Delete doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Delete doesn't work!", expected.getMake(), actual.getMake());
        Assert.assertEquals("Delete doesn't work!", expected.getModel(), actual.getModel());
        Assert.assertEquals("Delete doesn't work!", expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals("Delete doesn't work!", expected.getParts().get(0).getId(), actual.getParts().get(0).getId());
    }

    @Test
    public void carService_findCarByIdWithCorrectId_returnsCarServiceModel() {
        this.carRepository.saveAndFlush(this.modelMapper.map(this.carServiceModel, Car.class));
        String id = this.carRepository.findAll().get(0).getId();
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);
        CarServiceModel expected = this.carService.findCarById(id);
        Assert.assertEquals("Find doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Find doesn't work!", expected.getMake(), actual.getMake());
        Assert.assertEquals("Find doesn't work!", expected.getModel(), actual.getModel());
        Assert.assertEquals("Find doesn't work!", expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals("Find doesn't work!", expected.getParts().get(0).getId(), actual.getParts().get(0).getId());
    }


    //Tests with incorrect data:
    @Test(expected = Exception.class)
    public void carService_editCarWithFieldsValuesNull_throwsException() {
        CarServiceModel carServiceModel = this.carService.editCar(new CarServiceModel());
    }

    @Test(expected = Exception.class)
    public void carService_saveCarWithFieldsValuesNull_throwsException() {
        CarServiceModel carServiceModel = this.carService.saveCar(new CarServiceModel());
    }

    @Test(expected = Exception.class)
    public void carService_saveCarWithIdNull_throwsException() {
        CarServiceModel carServiceModel = this.carService.deleteCar(null);
    }

    @Test(expected = Exception.class)
    public void carService_findCarByIdWithIdNull_throwsException() {
        CarServiceModel carServiceModel = this.carService.findCarById(null);
    }

}
