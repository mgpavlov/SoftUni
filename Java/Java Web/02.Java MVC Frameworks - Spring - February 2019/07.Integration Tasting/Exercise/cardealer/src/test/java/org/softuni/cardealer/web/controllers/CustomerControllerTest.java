package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.repository.CustomerRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser
    @Transactional
    public void customersController_addCustomer_AddCustomerAndRedirectCorrectly() throws Exception {

        this.mvc
                .perform(post("/customers/add")
                        .param("name", "")
                        .param("birthDate", ""))
                .andExpect(redirectedUrl("add"));

        this.mvc
                .perform(post("/customers/add")
                        .param("name", "Misho")
                        .param("birthDate", "1999-09-19"))
                .andExpect(redirectedUrl("all"));

        Assert.assertEquals(1, this.customerRepository.count());
        Assert.assertEquals("Misho", this.customerRepository.findAll().get(0).getName());
    }

    @Test
    @WithMockUser
    @Transactional
    public void customers_ReturnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/customers/all"))
                .andExpect(view().name("all-customers"));
    }
}
