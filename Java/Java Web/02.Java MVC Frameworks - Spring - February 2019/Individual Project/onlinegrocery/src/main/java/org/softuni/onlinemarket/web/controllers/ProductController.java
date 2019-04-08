package org.softuni.onlinemarket.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.User;
import org.softuni.onlinemarket.domain.models.binding.ProductAddBindingModel;
import org.softuni.onlinemarket.domain.models.service.ProductServiceModel;
import org.softuni.onlinemarket.domain.models.service.UserServiceModel;
import org.softuni.onlinemarket.domain.models.view.CategoryViewModel;
import org.softuni.onlinemarket.domain.models.view.ProductAllViewModel;
import org.softuni.onlinemarket.domain.models.view.ProductDetailsViewModel;
import org.softuni.onlinemarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, UserService userService,
                             CloudinaryService cloudinaryService, CategoryService categoryService,
                             ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProduct(@ModelAttribute(name = "productBindingModel") ProductAddBindingModel productBindingModel, 
                                   ModelAndView modelAndView) {
        return loadAndReturnModelAndView(productBindingModel, modelAndView);
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProductConfirm(@Valid @ModelAttribute(name = "model")
                                                      ProductAddBindingModel model, BindingResult bindingResult,
                                          ModelAndView modelAndView) throws IOException {

        ProductServiceModel productServiceModel = modelMapper.map(model, ProductServiceModel.class);

        if (model.getImage().isEmpty()){
            return loadAndReturnModelAndView(model, modelAndView);
        }
        if (bindingResult.hasErrors() ||
                productService.createProduct(productServiceModel, model.getImage()) == null) {

            return loadAndReturnModelAndView(model, modelAndView);
        }

        return redirect("/products/all");
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allProducts(ModelAndView modelAndView) {
        List<ProductAllViewModel> allProducts = productService.findAllProducts()
                .stream()
                .map(p -> modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("products", allProducts);

        return view("product/all-products", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsProduct(@PathVariable String id, ModelAndView modelAndView) {
        ProductDetailsViewModel product = modelMapper.map(productService.findProductById(id), ProductDetailsViewModel.class);
        modelAndView.addObject("product", product);

        return view("product/details", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editProduct(@PathVariable String id, ModelAndView modelAndView) {
        return loadModelAndViewAndReturnView(id, modelAndView);
    }

    private ModelAndView loadModelAndViewAndReturnView(@PathVariable String id, ModelAndView modelAndView) {
        return loadAndReturnModelAndView(id, modelAndView, "product/edit-product");
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editProductConfirm(@PathVariable String id, @ModelAttribute ProductAddBindingModel model) {
        productService.editProduct(id, modelMapper.map(model, ProductServiceModel.class));

        return redirect("/products/details/" + id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteProduct(@PathVariable String id, ModelAndView modelAndView) {
        return loadAndReturnModelAndView(id, modelAndView, "product/delete-product");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteProductConfirm(@PathVariable String id) {
        productService.deleteProduct(id);

        return redirect("/products/all");
    }

    @GetMapping("/fetch/{category}")
    @ResponseBody
    public List<ProductAllViewModel> fetchByCategory(@PathVariable String category) {
        if(category.equals("all")) {
            return productService.findAllProducts()
                    .stream()
                    .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                    .collect(Collectors.toList());
        }

        return productService.findAllByCategory(category)
                .stream()
                .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<ProductAllViewModel> fetchAllProducts() {
        return productService.findAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/fetch/sale")
    @ResponseBody
    public List<ProductAllViewModel> fetchSaleProducts() {
        return productService.findAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());
    }
    
    private ModelAndView loadAndReturnModelAndView(ProductAddBindingModel productBindingModel, ModelAndView modelAndView) {
        List<CategoryViewModel> categories = categoryService.findAllCategories()
                .stream().map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
        
        modelAndView.addObject("model", productBindingModel);

        modelAndView.addObject("categories", categories);

        return view("product/add-product", modelAndView);
    }

    private ModelAndView loadAndReturnModelAndView(String id, ModelAndView modelAndView, String view) {
        ProductServiceModel productServiceModel = productService.findProductById(id);
        ProductAddBindingModel model = modelMapper.map(productServiceModel, ProductAddBindingModel.class);
        model.setCategories(productServiceModel.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));

        modelAndView.addObject("product", model);
        modelAndView.addObject("productId", id);

        return view(view, modelAndView);
    }
}
