package org.softuni.cardealer.web.controllers;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Supplier;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SuppliersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private Gson gson;


    @Test
    @WithMockUser
    @Transactional
    public void suppliersController_addSupplier_addSupplierAndRedirectCorrectly() throws Exception {
        this.mvc
                .perform(post("/suppliers/add")
                        .param("name", "misho")
                        .param("isImporter", "off"))
                .andExpect(view().name("redirect:all"));

        this.mvc
                .perform(post("/suppliers/add")
                        .param("name", "tosho")
                        .param("isImporter", "true"))
                .andExpect(view().name("redirect:all"));

        Supplier actualOne = this.supplierRepository.findByName("misho").orElse(null);
        Supplier actualTwo = this.supplierRepository.findByName("tosho").orElse(null);

        Assert.assertEquals(2, this.supplierRepository.count());

        Assert.assertEquals("misho", actualOne.getName());
        Assert.assertFalse(actualOne.getIsImporter());

        Assert.assertEquals("tosho", actualTwo.getName());
        Assert.assertTrue(actualTwo.getIsImporter());
    }

    @Test
    @WithMockUser
    @Transactional
    public void suppliersController_tryToAddIncorrectSupplier_redirectCorrectly() throws Exception {
        this.mvc
                .perform(post("/suppliers/add")
                        .param("name", "")
                        .param("isImporter", "off"))
                .andExpect(view().name("redirect:add"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void suppliersController_editSupplier_EditAndRedirectCorrectly() throws Exception {

        Supplier supplier1 = new Supplier();
        supplier1.setName("misho");
        supplier1.setIsImporter(true);

        Supplier supplier2 = new Supplier();
        supplier2.setName("pesho");
        supplier2.setIsImporter(false);

        supplier1 = this.supplierRepository.saveAndFlush(supplier1);
        supplier2 = this.supplierRepository.saveAndFlush(supplier2);

        this.mvc
                .perform(
                        post("/suppliers/edit/" + supplier1.getId())
                        .param("name", "Misho")
                        .param("isImporter", "false")
                )
                .andExpect(view().name("redirect:/suppliers/all"));

        this.mvc
                .perform(
                        post("/suppliers/edit/" + supplier2.getId())
                                .param("name", "")
                                .param("isImporter", "true")
                )
                .andExpect(view().name("redirect:/edit/" + supplier2.getId()));

        Supplier supplier1Actual = this.supplierRepository.findById(supplier1.getId()).orElse(null);
        Supplier supplier2Actual = this.supplierRepository.findById(supplier2.getId()).orElse(null);

        Assert.assertEquals("Misho", supplier1Actual.getName());
        Assert.assertFalse(supplier1Actual.getIsImporter());

        Assert.assertEquals("pesho", supplier2Actual.getName());
        Assert.assertFalse(supplier2Actual.getIsImporter());
    }

    @Test
    @WithMockUser
    @Transactional
    public void suppliersController_deleteSupplier_deleteSupplierCorrectly() throws Exception {

        Supplier supplier1 = new Supplier();
        supplier1.setName("misho");
        supplier1.setIsImporter(true);

        Supplier supplier2 = new Supplier();
        supplier2.setName("tosho");
        supplier2.setIsImporter(false);

        supplier1 = this.supplierRepository.saveAndFlush(supplier1);
        supplier2 = this.supplierRepository.saveAndFlush(supplier2);

        this.mvc
                .perform(
                        post("/suppliers/delete/" + supplier1.getId())
                )
                .andExpect(view().name("redirect:/suppliers/all"));

        Assert.assertEquals(1, this.supplierRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser
    @Transactional
    public void suppliersController_deleteUnexistingSupplier_throwException() throws Exception {
        this.mvc
                .perform(
                        post("/suppliers/delete/misho123")
                );
    }

    @Test
    @WithMockUser
    @Transactional
    public void suppliersController_allSuppliers_ReturnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void partsController_fetchParts_returnsCorrectResult() throws Exception{

        Supplier supplier1 = new Supplier();
        supplier1.setName("misho");
        supplier1.setIsImporter(true);

        Supplier supplier2 = new Supplier();
        supplier2.setName("tosho");
        supplier2.setIsImporter(false);

        supplier1 = this.supplierRepository.saveAndFlush(supplier1);
        supplier2 = this.supplierRepository.saveAndFlush(supplier2);

        String result = this.mvc
                .perform(get("/suppliers/fetch"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Supplier[] suppliers = this.gson.fromJson(result, Supplier[].class);


        Supplier actualFirst = this.supplierRepository.findById(supplier1.getId()).orElse(null);

        Assert.assertEquals(suppliers[0].getId(), actualFirst.getId());
        Assert.assertEquals(suppliers[0].getName(), actualFirst.getName());
        Assert.assertEquals(suppliers.length, this.supplierRepository.count());
    }
}
