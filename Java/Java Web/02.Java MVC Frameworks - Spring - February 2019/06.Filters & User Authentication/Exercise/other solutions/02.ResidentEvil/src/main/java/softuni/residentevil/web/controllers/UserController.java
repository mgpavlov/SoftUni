package softuni.residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.residentevil.domain.models.binding.UserRegisterBindingModel;
import softuni.residentevil.domain.models.service.UserServiceModel;
import softuni.residentevil.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController {
  private final UserService userService;
  private final ModelMapper modelMapper;

  @Autowired
  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }


  @GetMapping("/login")
  @PreAuthorize("isAnonymous()")
  public ModelAndView login() {
    return super.view("login");
  }

  @GetMapping("/register")
  @PreAuthorize("isAnonymous()")
  public ModelAndView register() {
    return super.view("register");
  }

  @PostMapping("/register")
  @PreAuthorize("isAnonymous()")
  public ModelAndView registerConfirm(@Valid @ModelAttribute(name = "bindingModel")
                                          UserRegisterBindingModel bindingModel,
                                      ModelAndView modelAndView) {
    if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
      return super.view("/register");
    } else {
      this.userService.register(this.modelMapper.map(bindingModel, UserServiceModel.class));
      return super.redirect("/login");
    }
  }

  @GetMapping("/unauthorized")
  public ModelAndView unauthorized(){
    return super.view("error/unauthorized");
  }
}
