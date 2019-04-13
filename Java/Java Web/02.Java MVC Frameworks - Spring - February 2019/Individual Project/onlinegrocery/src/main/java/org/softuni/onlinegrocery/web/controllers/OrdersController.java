package org.softuni.onlinegrocery.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.domain.entities.enumeration.Status;
import org.softuni.onlinegrocery.domain.models.service.OrderProductServiceModel;
import org.softuni.onlinegrocery.domain.models.service.OrderServiceModel;
import org.softuni.onlinegrocery.domain.models.view.*;
import org.softuni.onlinegrocery.service.OrderService;
import org.softuni.onlinegrocery.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrdersController extends BaseController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrdersController(OrderService orderService, ModelMapper modelMapper){
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Orders")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {

        List<OrderViewModel> viewModels = mapListOrderServiceToViewModel(orderService.findAllOrders());

        modelAndView.addObject("orders", viewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        OrderDetailsViewModel order = loadOrderDetailsViewModel(id);

        modelAndView.addObject("order", order);

        return view("order/order-products", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Orders")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        String customerName = principal.getName();

        List<OrderViewModel> myOrders =
                mapListOrderServiceToViewModel(orderService.findOrdersByCustomer(customerName));

        List<MyOrderViewModel> myPendingOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Pending));

        List<MyOrderViewModel> myShippedOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Shipped));

        List<MyOrderViewModel> myDeliveredOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Delivered));

        modelAndView.addObject("orders", myOrders);
        modelAndView.addObject("myPendingOrders", myPendingOrders);
        modelAndView.addObject("myShippedOrders", myShippedOrders);
        modelAndView.addObject("myDeliveredOrders", myDeliveredOrders);

        return view("order/my-orders", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Order Details")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        OrderDetailsViewModel order = loadOrderDetailsViewModel(id);

        modelAndView.addObject("order", order);

        return view("order/order-details", modelAndView);
    }

    @GetMapping("/change/status/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderChangeStatus(@PathVariable String id) {

        orderService.changeOrderStatus(id);

        return redirect("/order/all");
    }


    @GetMapping("/fetch/{status}")
    @ResponseBody
    public List<OrderViewModel> fetchByCategory(@PathVariable String status) {

        return loadOrdersByStatus(status);

    }

    private List<OrderViewModel> loadOrdersByStatus(String status) {

        Status statusStatus = Status.Pending;

        switch (status){
            case "All":
                return mapListOrderServiceToViewModel(orderService.findAllOrders());
            case "Shipped":
                statusStatus = Status.Shipped;
                break;
            case "Delivered":
                statusStatus = Status.Delivered;
                break;
            case "Acquired":
                statusStatus = Status.Acquired;
                break;
        }

        return mapListOrderServiceToViewModel(orderService.findOrdersByStatus(statusStatus));
    }

    private OrderDetailsViewModel loadOrderDetailsViewModel(String id) {
        OrderServiceModel orderServiceModel = orderService.findOrderById(id);
        List<OrderProductServiceModel> products = orderServiceModel.getProducts();

        OrderDetailsViewModel order = modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);
        List<ShoppingCartItem> items = new ArrayList<>();

        Map<OrderProductServiceModel, Integer> productItems = new HashMap<>();

        for (OrderProductServiceModel product: products) {
            productItems.putIfAbsent(product, 0);
            int quantity = productItems.get(product) + 1;
            productItems.put(product, quantity);
        }

        for (Map.Entry<OrderProductServiceModel, Integer> productKVP : productItems.entrySet()) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setQuantity(productKVP.getValue());
            OrderProductViewModel orderProductViewModel = modelMapper.map(productKVP.getKey(), OrderProductViewModel.class);
            shoppingCartItem.setProduct(orderProductViewModel);

            items.add(shoppingCartItem);
        }
        order.setItems(items);

        return order;
    }

    private List<OrderViewModel> mapListOrderServiceToViewModel(List<OrderServiceModel> orderServiceModel){
        return orderServiceModel.stream()
                .map(order -> modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    private List<MyOrderViewModel> mapListOrderServiceToMyViewModel(List<OrderServiceModel> orderServiceModel){
        return orderServiceModel.stream()
                .map(order -> modelMapper.map(order, MyOrderViewModel.class))
                .collect(Collectors.toList());
    }

}
