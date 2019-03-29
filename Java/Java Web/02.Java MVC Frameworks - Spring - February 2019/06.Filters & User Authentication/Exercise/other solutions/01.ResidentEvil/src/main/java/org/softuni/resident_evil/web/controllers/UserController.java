package org.softuni.resident_evil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.models.binding.UserEditBindingModel;
import org.softuni.resident_evil.domain.models.binding.UserRegisterBindingModel;
import org.softuni.resident_evil.domain.models.service.UserServiceModel;
import org.softuni.resident_evil.domain.models.view.UserViewModel;
import org.softuni.resident_evil.service.UserRoleService;
import org.softuni.resident_evil.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final ModelMapper mapper;
    private final UserService userService;
    private final UserRoleService roleService;

    public UserController(ModelMapper mapper, UserService userService, UserRoleService roleService) {
        this.mapper = mapper;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public ModelAndView registerView(@ModelAttribute("model") UserRegisterBindingModel model){
        return this.view("registration-form");
    }

    @GetMapping("/login")
    public ModelAndView loginView(){
        return this.view("login-form");
    }

    @PostMapping("/register")
    public ModelAndView registerAction(@Valid @ModelAttribute("model") UserRegisterBindingModel model,
                                       BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return this.view("registration-form");
        }
        UserServiceModel serviceModel = this.mapper.map(model, UserServiceModel.class);
        if (this.userService.saveUser(serviceModel)) {
            return redirect("/user/login");
        }
        return super.view("/registration-form");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ModelAndView allUsersView() {
        List<UserServiceModel> serviceUserModels = this.userService.getAllUsers();
        List<UserViewModel> viewUserModels = serviceUserModels
                .stream()
                .map(serviceModel -> this.mapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        return view("/all-users")
                .addObject("allUsers", viewUserModels);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit/{username}")
    public ModelAndView ditUserView(@AuthenticationPrincipal UserDetails currentUser,
                                    @PathVariable String username) {
        if(currentUser.getUsername().equals(username)){
            return this.redirect("/user/list");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(username);
        UserEditBindingModel model = this.mapper.map(userDetails, UserEditBindingModel.class);

        return view("/edit-user")
                .addObject("allRoles", this.roleService.getRoles())
                .addObject("model", model);

    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editAction(@ModelAttribute("model") UserEditBindingModel editBindingModel) {


        if (this.userService.editUser(editBindingModel)) {
            return redirect("/user/list");
        }


        return redirect("/user/edit/" + editBindingModel.getUsername());
    }
}
