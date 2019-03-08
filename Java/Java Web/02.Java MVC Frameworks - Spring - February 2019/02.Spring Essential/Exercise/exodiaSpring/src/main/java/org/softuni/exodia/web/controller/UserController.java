package org.softuni.exodia.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.model.binding.UserLoginBindingModel;
import org.softuni.exodia.domain.model.binding.UserRegisterBindingModel;
import org.softuni.exodia.domain.model.service.UserServiceModel;
import org.softuni.exodia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, HttpSession session) {
        if (this.isLoggedIn(session)) {
            return this.view("redirect:/home", modelAndView);
        }
        return this.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView confirmRegister(@ModelAttribute(name = "model") UserRegisterBindingModel model, ModelAndView modelAndView) {
        if (!model.getConfirmPassword().equals(model.getPassword()) ||
                !this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class))) {
            return this.view("redirect:/register", modelAndView);
        }
        return this.view("redirect:/login", modelAndView);
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession session) {
        if (this.isLoggedIn(session)) {
            return this.view("redirect:/home", modelAndView);
        }
        return this.view("login", modelAndView);
    }

    @PostMapping("/login")
    public ModelAndView confirmLogin(@ModelAttribute(name = "model") UserLoginBindingModel model,
                                     ModelAndView modelAndView, HttpSession session) {
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        if (!this.userService.loginUser(userServiceModel)) {
            return this.view("redirect:/login", modelAndView);
        }
        session.setAttribute("userId", userServiceModel.getId());
        session.setAttribute("username", userServiceModel.getUsername());
        return this.view("redirect:/home", modelAndView);
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate();
        return this.view("redirect:/", modelAndView);
    }
}
