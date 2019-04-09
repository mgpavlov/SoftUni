package org.softuni.onlinegrocery.web.controllers;

import org.modelmapper.ModelMapper;

import org.softuni.onlinegrocery.domain.models.view.CategoryViewModel;
import org.softuni.onlinegrocery.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
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
        return view("/index", modelAndView);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView renderHomePage(Principal principal, ModelAndView modelAndView) {
        
        List<CategoryViewModel> categories = categoryService.findAllCategories().stream()
                .map(category -> modelMapper.map(category, CategoryViewModel.class))
                .collect(Collectors.toList());
        
        modelAndView.addObject("principal", principal);
        modelAndView.addObject("categories", categories);
        return view("/home", modelAndView);
    }
}
