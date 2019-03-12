package exodia.web.controllers;

import exodia.domain.models.bindings.UserLoginBindingModel;
import exodia.domain.models.bindings.UserRegisterBindingModel;
import exodia.domain.models.services.UserServiceModel;
import exodia.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static exodia.constants.ConstantMessages.*;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }


    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel registerBindingModel, ModelAndView modelAndView) {

        if (!registerBindingModel.getPassword().equals(registerBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException(PASSWORDS_DONT_MATCH);
        }
        if (!this.userService.registerUser(this.modelMapper.map(registerBindingModel, UserServiceModel.class))) {
            throw new IllegalArgumentException(USER_REGISTRATION_FAILED);
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {

        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
            session.invalidate();
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }


    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel loginBindingModel, ModelAndView modelAndView,
                                     HttpSession session) {
        UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(loginBindingModel, UserServiceModel.class));
        if (userServiceModel == null) {
            throw new IllegalArgumentException(USER_LOGIN_FAILED);
        }

        session.setAttribute("userId", userServiceModel.getId());
        session.setAttribute("username", userServiceModel.getUsername());

        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

}
