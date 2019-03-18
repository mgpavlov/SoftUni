package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTests {
    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerService customerService;
    private CustomerServiceModel model;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.customerService = new CustomerServiceImpl(this.customerRepository, this.modelMapper);
        this.model = new CustomerServiceModel() {{
            setName("Misho");
            setBirthDate(LocalDate.now());
            setYoungDriver(true);
        }};
    }

    //Tests with correct data:
    @Test
    public void customerService_saveCustomerWithCorrectParameters_returnsServiceModel() {
        CustomerServiceModel actual = this.customerService.saveCustomer(this.model);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);
        Assert.assertEquals("Save doesn't work!", actual.getId(), expected.getId());
        Assert.assertEquals("Save doesn't work!", actual.getName(), expected.getName());
        Assert.assertEquals("Save doesn't work!", actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals("Save doesn't work!", actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test
    public void customerService_editCustomerWithCorrectParameters_returnsServiceModel() {
        this.customerRepository.saveAndFlush(this.modelMapper.map(model, Customer.class));
        CustomerServiceModel editedModel = new CustomerServiceModel() {{
            setId(customerRepository.findAll().get(0).getId());
            setName("Pesho");
            setBirthDate(LocalDate.now().plusDays(3));
            setYoungDriver(false);
        }};
        CustomerServiceModel actual = this.customerService.editCustomer(editedModel);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);
        Assert.assertEquals("Edit doesn't work!", actual.getId(), expected.getId());
        Assert.assertEquals("Edit doesn't work!", actual.getName(), expected.getName());
        Assert.assertEquals("Edit doesn't work!", actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals("Edit doesn't work!", actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test
    public void customerService_deleteCustomerWithCorrectId_returnsServiceModel() {
        this.customerRepository.saveAndFlush(this.modelMapper.map(this.model, Customer.class));
        String id = this.customerRepository.findAll().get(0).getId();
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);
        CustomerServiceModel actual = this.customerService.deleteCustomer(id);
        Assert.assertEquals("Delete doesn't work!", actual.getId(), expected.getId());
        Assert.assertEquals("Delete doesn't work!", actual.getName(), expected.getName());
        Assert.assertEquals("Delete doesn't work!", actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals("Delete doesn't work!", actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test
    public void customerService_findCustomerByIdWithCorrectId_returnsServiceModel() {
        this.customerRepository.saveAndFlush(this.modelMapper.map(this.model, Customer.class));
        String id = this.customerRepository.findAll().get(0).getId();
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);
        CustomerServiceModel actual = this.customerService.findCustomerById(id);
        Assert.assertEquals("Find doesn't work!", actual.getId(), expected.getId());
        Assert.assertEquals("Find doesn't work!", actual.getName(), expected.getName());
        Assert.assertEquals("Find doesn't work!", actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals("Find doesn't work!", actual.isYoungDriver(), expected.isYoungDriver());
    }

    //Tests with incorrect data:
    @Test(expected = Exception.class)
    public void customerService_saveCustomerWithFieldsValuesNull_throwsException() {
        CustomerServiceModel customerServiceModel = this.customerService.saveCustomer(new CustomerServiceModel());
    }

    @Test(expected = Exception.class)
    public void customerService_editCustomerWithFieldsValuesNull_throwsException() {
        CustomerServiceModel customerServiceModel = this.customerService.editCustomer(new CustomerServiceModel());
    }

    @Test(expected = Exception.class)
    public void customerService_saveCustomerWithIdNull_throwsException() {
        CustomerServiceModel customerServiceModel = this.customerService.deleteCustomer(null);
    }

    @Test(expected = Exception.class)
    public void customerService_findCustomerByIdWithIdNull_throwsException() {
        CustomerServiceModel customerById = this.customerService.findCustomerById(null);
    }

}
