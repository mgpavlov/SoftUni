package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PartRepository partRepository;

    @Test
    @WithMockUser("spring")
    @Transactional
    public void carsController_addCar_addCarAndRedirectCorrectly() throws Exception {

        Supplier supplier = createSupplierEntity("misho", true);
        Part part = createPartEntity("p1", 10, supplier);


        this.mvc
                .perform(post("/cars/add")
                        .param("make", "")
                        .param("model", "")
                        .param("travelledDistance", "")
                        .param("parts", ""))
                .andExpect(redirectedUrl("add"));
        this.mvc
                .perform(post("/cars/add")
                        .param("make", "vw")
                        .param("model", "golf")
                        .param("travelledDistance", "100000")
                        .param("parts", "p1"))
                .andExpect(redirectedUrl("all"));

        Car actual = this.carRepository.findAll().get(0);

        Assert.assertEquals(1, this.carRepository.count());
        Assert.assertEquals("vw", actual.getMake());
    }

    @Test
    @WithMockUser
    @Transactional
    public void carsController_editCar_editAndRedirectCorrectly() throws Exception {

        Supplier supplier = createSupplierEntity("misho", true);
        Part part = createPartEntity("p1", 10, supplier);
        Car car = createCarEntity("bmw","X5", 200000L, part);

        this.mvc
                .perform(post("/cars/edit/" + car.getId())
                        .param("make", "vw")
                        .param("model", "golf")
                        .param("travelledDistance", car.getTravelledDistance().toString())
                        .param("parts", "p1"))
                .andExpect(redirectedUrl("/cars/all"));

        this.mvc
                .perform(post("/cars/edit/" + car.getId())
                        .param("make", "")
                        .param("model", "")
                        .param("travelledDistance", car.getTravelledDistance().toString())
                        .param("parts", "p1"))
                .andExpect(redirectedUrl("/edit/" + car.getId()));


        Car actual = this.carRepository.findAll().get(0);

        Assert.assertEquals(car.getId(), actual.getId());
        Assert.assertEquals("vw", actual.getMake());
    }

    @Test
    @WithMockUser
    @Transactional
    public void carsController_deleteCar_deleteAndRedirectCorrectly() throws Exception {
        Supplier supplier = createSupplierEntity("misho", true);
        Part part = createPartEntity("p1", 10, supplier);
        Car car = createCarEntity("bmw","X5", 200000L, part);

        this.mvc
                .perform(post("/cars/delete/" + car.getId()))
                .andExpect(redirectedUrl("/cars/all"));

        Assert.assertEquals(0, this.carRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser
    @Transactional
    public void carsController_deleteNotExistingCar_throwException() throws Exception {
        this.mvc
                .perform(
                        post("/cars/delete/misho123")
                );
    }

    @Test
    @WithMockUser
    @Transactional
    public void carsController_allCars_returnsAllCarsAndViewCorrectly() throws Exception {

        this.mvc
                .perform(get("/cars/all"))
                .andExpect(model().attributeExists("cars"));
        this.mvc
                .perform(get("/cars/all"))
                .andExpect(view().name("all-cars"));
    }

    private Supplier createSupplierEntity(String name, boolean isImporter) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setIsImporter(isImporter);
        supplier = this.supplierRepository.saveAndFlush(supplier);
        return supplier;
    }


    private Part createPartEntity(String name, int price, Supplier supplier) {
        Part part = new Part();
        part.setName(name);
        part.setPrice(BigDecimal.valueOf(price));
        part.setSupplier(supplier);
        part = this.partRepository.saveAndFlush(part);
        return part;
    }

    private Car createCarEntity(String make, String model, Long travelledDistance, Part part) {
        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setTravelledDistance(travelledDistance);
        car.setParts(new ArrayList<>());
        car.getParts().add(part);
        car = this.carRepository.saveAndFlush(car);
        return car;
    }
}
