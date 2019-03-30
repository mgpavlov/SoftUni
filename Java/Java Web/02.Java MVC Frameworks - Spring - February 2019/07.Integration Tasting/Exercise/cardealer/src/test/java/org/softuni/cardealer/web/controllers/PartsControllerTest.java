package org.softuni.cardealer.web.controllers;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private Gson gson;

    @Before
    public void emptyDb() {
        this.partRepository.deleteAll();
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_addPart_addPartAndRedirectCorrectly() throws Exception {
        Supplier supplier = createSupplierEntity();


        this.mvc
                .perform(post("/parts/add")
                        .param("name", "p1")
                        .param("price", "1")
                        .param("supplier", supplier.getName()))
                .andExpect(redirectedUrl("all"));
        this.mvc
                .perform(post("/parts/add")
                        .param("name", "")
                        .param("price", "")
                        .param("supplier", supplier.getName()))
                .andExpect(redirectedUrl("add"));


        Part actual = this.partRepository.findAll().get(0);

        Assert.assertEquals(1, this.partRepository.count());
        Assert.assertEquals("p1", actual.getName());
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_editPart_editAndRedirectCorrectly() throws Exception {
        Supplier supplier = createSupplierEntity();
        Part part = createPartEntity("p1", 10, supplier);
        part = this.partRepository.saveAndFlush(part);

        this.mvc
                .perform(post("/parts/edit/" + part.getId())
                        .param("name", "p2")
                        .param("price", part.getPrice().toString())
                        .param("supplier", supplier.getName()))
                .andExpect(redirectedUrl("/parts/all"));

        this.mvc
                .perform(post("/parts/edit/" + part.getId())
                        .param("name", "")
                        .param("price", part.getPrice().toString())
                        .param("supplier", supplier.getName()))
                .andExpect(redirectedUrl("edit/" + part.getId()));

        Part actual = this.partRepository.findAll().get(0);

        Assert.assertEquals(part.getId(), actual.getId());
        Assert.assertEquals("p2", actual.getName());
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_deletePart_deleteAndRedirectCorrectly() throws Exception {
        Supplier supplier = createSupplierEntity();
        Part part = createPartEntity("p1", 10, supplier);
        part = this.partRepository.saveAndFlush(part);

        this.mvc
                .perform(post("/parts/delete/" + part.getId()))
                .andExpect(redirectedUrl("/parts/all"));

        Assert.assertEquals(0, this.partRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser
    @Transactional
    public void partsController_deleteUnexistingPart_throwException() throws Exception {
        this.mvc
                .perform(
                        post("/parts/delete/misho123")
                );
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_allParts_returnsAllPartsAndViewCorrectly() throws Exception {

        this.mvc
                .perform(get("/parts/all"))
                .andExpect(model().attributeExists("parts"));
        this.mvc
                .perform(get("/parts/all"))
                .andExpect(view().name("all-parts"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_fetchParts_returnsCorrectResult() throws Exception{
        Supplier supplier = createSupplierEntity();

        Part one = createPartEntity("p1", 10, supplier);
        one = this.partRepository.saveAndFlush(one);

        Part two = createPartEntity("p2", 10, supplier);
        two = this.partRepository.saveAndFlush(two);

        String result = this.mvc
                .perform(get("/parts/fetch"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Part[] parts = this.gson.fromJson(result, Part[].class);


        Part actualFirst = this.partRepository.findById(one.getId()).orElse(null);

        Assert.assertEquals(parts[0].getId(), actualFirst.getId());
        Assert.assertEquals(parts[0].getName(), actualFirst.getName());
        Assert.assertEquals(parts.length, this.partRepository.count());
    }

    private Supplier createSupplierEntity() {
        Supplier supplier = new Supplier();
        supplier.setName("misho");
        supplier.setIsImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);
        return supplier;
    }


    private Part createPartEntity(String name, int price, Supplier supplier) {
        Part part = new Part();
        part.setName(name);
        part.setPrice(BigDecimal.valueOf(price));
        part.setSupplier(supplier);
        return part;
    }
}