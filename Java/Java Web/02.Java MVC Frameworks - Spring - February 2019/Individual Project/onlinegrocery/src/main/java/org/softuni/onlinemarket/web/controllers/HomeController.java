package org.softuni.onlinemarket.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.models.view.CategoryViewModel;
import org.softuni.onlinemarket.service.CategoryService;
import org.softuni.onlinemarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView renderIndexPage(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("principal", principal);
        return super.view("/index", modelAndView);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView renderHomePage(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("principal", principal);
        modelAndView.addObject("categories",
                this.categoryService.findAllCategories().stream()
                        .map(category -> this.modelMapper.map(category, CategoryViewModel.class))
                        .collect(Collectors.toList()));
        return super.view("/home", modelAndView);
    }

    /*@GetMapping("/home2")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView renderHomeCategoryPage(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("principal", principal);
        modelAndView.addObject("categories",
                this.categoryService.findAllCategories().stream()
                        .map(category -> this.modelMapper.map(category, CategoryViewModel.class))
                        .collect(Collectors.toList()));
        return super.view("/home2", modelAndView);
    }*/
}
