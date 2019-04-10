package org.softuni.onlinegrocery.web.controllers;

import org.softuni.onlinegrocery.domain.models.binding.UserRegisterBindingModel;
import org.softuni.onlinegrocery.domain.models.service.UserServiceModel;
import org.softuni.onlinegrocery.domain.models.view.UsersViewModel;
import org.softuni.onlinegrocery.service.UserService;
import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Register")
    public ModelAndView renderRegister() {
        return super.view("/register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterBindingModel bindingModel,
                                 ModelAndView modelAndView) {
        bindingModel.setAddress(htmlEscape(bindingModel.getAddress()));
        bindingModel.setEmail(htmlEscape(bindingModel.getEmail()));
        bindingModel.setUsername(htmlEscape(bindingModel.getUsername()));
        bindingModel.setPassword(htmlEscape(bindingModel.getPassword()));
        bindingModel.setConfirmPassword(htmlEscape(bindingModel.getConfirmPassword()));
        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return redirect("/register");
        }
        UserServiceModel serviceModel = this.modelMapper.map(bindingModel, UserServiceModel.class);
        this.userService.register(serviceModel);

        return redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Login")
    public ModelAndView login(@RequestParam(required = false) String error, ModelAndView modelAndView) {
        if (error != null) {
            modelAndView.addObject("error", "Error");
        }

        return view("/login", modelAndView);
    }

    @GetMapping("/user/profile/{id}")
    public ModelAndView renderProfilePage(@PathVariable("id") String id, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findById(id);
        UsersViewModel usersViewModel = this.modelMapper.map(userServiceModel, UsersViewModel.class);
        modelAndView.addObject("viewModel", usersViewModel);

        return super.view("/profile", modelAndView);
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Users")
    public ModelAndView renderAllUsersPage() {
        return super.view("/users-all");
    }

    @PostMapping("/users/edit/role/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUserRole(@PathVariable("id") String id, String role, Principal principal) {
        UserServiceModel currentLoggedUser = this.userService.findByUsername(principal.getName());
        if (currentLoggedUser.getId().equals(id)) {
            return super.redirect("/user/profile/" + id);
        }

        try {
            this.userService.updateRole(id, role);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        return super.redirect("/user/profile/" + id);
    }

    @GetMapping("/api/users")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UsersViewModel> allUsers() {
        return this.userService.findAllUsers()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, UsersViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/users/find")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsersViewModel allUsers(@RequestParam("username") String username) {
        UserServiceModel byUsername = this.userService.findByUsername(username);

        return byUsername == null ? new UsersViewModel()
                : this.modelMapper.map(byUsername, UsersViewModel.class);
    }

    private String htmlEscape(String input){
        input = input.replaceAll("&", "&amp;")
              .replaceAll("<", "&lt;")
              .replaceAll(">", "&gt;")
              .replaceAll("\"", "&quot;");

        return input;
    }
}
