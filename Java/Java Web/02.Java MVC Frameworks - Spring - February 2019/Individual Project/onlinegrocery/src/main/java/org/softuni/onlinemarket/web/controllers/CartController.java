package org.softuni.onlinemarket.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.enumeration.Status;
import org.softuni.onlinemarket.domain.models.service.OrderServiceModel;
import org.softuni.onlinemarket.domain.models.service.ProductServiceModel;
import org.softuni.onlinemarket.domain.models.service.UserServiceModel;
import org.softuni.onlinemarket.domain.models.view.ProductDetailsViewModel;
import org.softuni.onlinemarket.domain.models.view.ShoppingCartItem;
import org.softuni.onlinemarket.service.OrderService;
import org.softuni.onlinemarket.service.ProductService;
import org.softuni.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartController(ProductService productService, UserService userService,
                          OrderService orderService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add-product")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addToCartConfirm(String id, int quantity, HttpSession session) {
        ProductDetailsViewModel product = modelMapper
                .map(productService.findProductById(id), ProductDetailsViewModel.class);

        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        var cart = retrieveCart(session);
        addItemToCart(cartItem, cart);

        return redirect("/home");
    }

    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView cartDetails(ModelAndView modelAndView, HttpSession session) {
        var cart = retrieveCart(session);
        modelAndView.addObject("totalPrice", calcTotal(cart));

        return view("cart/cart-details", modelAndView);
    }

    @DeleteMapping("/remove-product")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView removeFromCartConfirm(String id, HttpSession session) {
        removeItemFromCart(id, retrieveCart(session));

        return redirect("/cart/details");
    }

    @PostMapping("/checkout")
    public ModelAndView checkoutConfirm(HttpSession session, Principal principal) {
        var cart = retrieveCart(session);

        OrderServiceModel orderServiceModel = prepareOrder(cart, principal.getName());
        orderService.createOrder(orderServiceModel);
        return redirect("/home");
    }

    private List<ShoppingCartItem> retrieveCart(HttpSession session) {
        initCart(session);

        return (List<ShoppingCartItem>) session.getAttribute("shopping-cart");
    }

    private void initCart(HttpSession session) {
        if (session.getAttribute("shopping-cart") == null) {
            session.setAttribute("shopping-cart", new LinkedList<>());
        }
    }

    private void addItemToCart(ShoppingCartItem item, List<ShoppingCartItem> cart) {
        for (ShoppingCartItem shoppingCartItem : cart) {
            if (shoppingCartItem.getProduct().getId().equals(item.getProduct().getId())) {
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + item.getQuantity());
                return;
            }
        }

        cart.add(item);
    }

    private void removeItemFromCart(String id, List<ShoppingCartItem> cart) {
        cart.removeIf(ci -> ci.getProduct().getId().equals(id));
    }

    private BigDecimal calcTotal(List<ShoppingCartItem> cart) {
        BigDecimal result = new BigDecimal(0);
        for (ShoppingCartItem item : cart) {
            result = result.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        return result;
    }

    private OrderServiceModel prepareOrder(List<ShoppingCartItem> cart, String customerName) {
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        UserServiceModel customer = userService.findUserByUserName(customerName);
        orderServiceModel.setCustomer(customer);
        orderServiceModel.setShippingAddress(customer.getAddress());
        orderServiceModel.setStatus(Status.Pending);

        List<ProductServiceModel> products = new ArrayList<>();
        for (ShoppingCartItem item : cart) {
            ProductServiceModel productServiceModel = modelMapper.map(item.getProduct(), ProductServiceModel.class);

            for (int i = 0; i < item.getQuantity(); i++) {
                products.add(productServiceModel);
            }
        }

        orderServiceModel.setProducts(products);
        orderServiceModel.setTotalPrice(calcTotal(cart));

        return orderServiceModel;
    }
}
