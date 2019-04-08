package org.softuni.onlinemarket.web.controllers;

import org.modelmapper.ModelMapper;

import org.softuni.onlinemarket.domain.models.binding.CategoryAddBindingModel;
import org.softuni.onlinemarket.domain.models.service.CategoryServiceModel;
import org.softuni.onlinemarket.domain.models.view.CategoryViewModel;
import org.softuni.onlinemarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory() {
        return view("category/add-category");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@ModelAttribute CategoryAddBindingModel model) {

        CategoryServiceModel categoryServiceModel = modelMapper.map(model, CategoryServiceModel.class);

        categoryService.addCategory(categoryServiceModel);

        return redirect("/categories/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCategories(ModelAndView modelAndView) {

        List<CategoryViewModel> categories = categoryService.findAllCategories()
                .stream()
                .map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("categories", categories);

        return view("category/all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel = modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);

        modelAndView.addObject("model",categoryViewModel);

        return view("category/edit-category", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategoryConfirm(@PathVariable String id, @ModelAttribute CategoryAddBindingModel model) {

        CategoryServiceModel categoryServiceModel = modelMapper.map(model, CategoryServiceModel.class);

        categoryService.editCategory(id, categoryServiceModel);

        return redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel = modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);

        modelAndView.addObject("model",categoryViewModel);

        return view("category/delete-category", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategoryConfirm(@PathVariable String id) {

        categoryService.deleteCategory(id);

        return redirect("/categories/all");
    }

    @GetMapping("/fetch")
    /*@PreAuthorize("hasRole('ROLE_MODERATOR')")*/
    @ResponseBody
    public List<CategoryViewModel> fetchCategories() {
        return categoryService.findAllCategories()
                .stream()
                .map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
    }
}
