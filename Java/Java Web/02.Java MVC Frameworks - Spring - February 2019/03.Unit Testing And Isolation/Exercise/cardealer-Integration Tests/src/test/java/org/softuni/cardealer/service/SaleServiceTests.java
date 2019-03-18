package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.models.service.*;
import org.softuni.cardealer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceTests {
    @Autowired
    private CarSaleRepository carSaleRepository;
    @Autowired
    private PartSaleRepository partSaleRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private SaleService saleService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.saleService = new SaleServiceImpl(this.carSaleRepository, this.partSaleRepository, this.modelMapper);
        CustomerServiceModel customer = new CustomerServiceModel() {{
            setName("Misho");
            setBirthDate(LocalDate.now());
            setYoungDriver(true);
        }};
        PartServiceModel part = new PartServiceModel() {{
            setName("shaft");
            setPrice(new BigDecimal("112.88"));
        }};
        CarServiceModel car = new CarServiceModel() {{
            setMake("BMW");
            setModel("7");
            setTravelledDistance(35000L);
        }};

        customerRepository.saveAndFlush(this.modelMapper.map(customer, Customer.class));
        partRepository.saveAndFlush(this.modelMapper.map(part, Part.class));
        carRepository.saveAndFlush(this.modelMapper.map(car, Car.class));
    }

    //Tests with correct data:
    @Test
    public void saleService_saleCarWithCorrectFieldsValues_returnsServiceModel() {
        CarSaleServiceModel model = new CarSaleServiceModel() {{
            setCar(modelMapper.map(carRepository.findAll().get(0), CarServiceModel.class));
            setCustomer(modelMapper.map(customerRepository.findAll().get(0), CustomerServiceModel.class));
            setDiscount(2D);
        }};
        CarSaleServiceModel actual = this.saleService.saleCar(model);
        CarSaleServiceModel expected = this.modelMapper
                .map(this.carSaleRepository.findAll().get(0), CarSaleServiceModel.class);
        Assert.assertEquals("Sale Car doesn't work", actual.getCar().getId(), expected.getCar().getId());
        Assert.assertEquals("Sale Car doesn't work", actual.getId(), expected.getId());
        Assert.assertEquals("Sale Car doesn't work", actual.getCustomer().getId(), expected.getCustomer().getId());
        Assert.assertEquals("Sale Car doesn't work", actual.getDiscount(), expected.getDiscount());
    }

    @Test
    public void saleService_salePartWithCorrectFieldsValues_returnsServiceModel() {
        PartSaleServiceModel model = new PartSaleServiceModel() {{
            setPart(modelMapper.map(partRepository.findAll().get(0), PartServiceModel.class));
            setCustomer(modelMapper.map(customerRepository.findAll().get(0), CustomerServiceModel.class));
            setQuantity(666);
            setDiscount(333.33);
        }};
        PartSaleServiceModel actual = this.saleService.salePart(model);
        PartSaleServiceModel expected = this.modelMapper
                .map(this.partSaleRepository.findAll().get(0), PartSaleServiceModel.class);
        Assert.assertEquals("Sale Part doesn't work", actual.getPart().getName(), expected.getPart().getName());
        Assert.assertEquals("Sale Part doesn't work", actual.getId(), expected.getId());
        Assert.assertEquals("Sale Part doesn't work", actual.getCustomer().getName(), expected.getCustomer().getName());
        Assert.assertEquals("Sale Part doesn't work", actual.getDiscount(), expected.getDiscount());
    }

    //Tests with incorrect data:
    @Test(expected = Exception.class)
    public void saleService_salePartWithFieldsValuesNull_throwsException() {
        PartSaleServiceModel partSaleServiceModel = this.saleService.salePart(new PartSaleServiceModel());
    }

    @Test(expected = Exception.class)
    public void saleService_saleCarWithFieldsValuesNull_throwsException() {
        CarSaleServiceModel carSaleServiceModel = this.saleService.saleCar(new CarSaleServiceModel());
    }
}
