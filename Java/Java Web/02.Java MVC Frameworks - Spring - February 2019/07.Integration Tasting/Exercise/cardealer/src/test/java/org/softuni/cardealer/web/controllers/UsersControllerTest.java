package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class  UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void login_ReturnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/users/login"))
                .andExpect(view().name("/login"));
    }

    @Test
    @Transactional
    public void register_ReturnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/users/register"))
                .andExpect(view().name("/register"));
    }

    @Test
    @Transactional
    public void register_registersUserCorrectly_redirectCorrectly() throws Exception {
        this.mvc
                .perform(
                        post("/users/register")
                                .param("username", "misho")
                                .param("password", "misho")
                                .param("confirmPassword", "misho")
                                .param("email", "misho@m.m")
                ).andExpect(view().name("redirect:/login"));

        Assert.assertEquals(1, this.userRepository.count());
        Assert.assertEquals("misho@m.m", this.userRepository.findByUsername("misho").orElse(null).getEmail());
    }

    @Test
    @Transactional
    public void register_tryRegisterUserIncorrectParams_redirectRegister() throws Exception {

        this.mvc
                .perform(
                        post("/users/register")
                                .param("username", "")
                                .param("password", "")
                                .param("confirmPassword", "pesho")
                                .param("email", "misho@m.m")
                ).andExpect(view().name("redirect:/register"));
    }
}
