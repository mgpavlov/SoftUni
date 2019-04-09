package org.softuni.onlinegrocery.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.domain.entities.enumeration.Status;
import org.softuni.onlinegrocery.domain.models.service.OrderServiceModel;
import org.softuni.onlinegrocery.domain.models.view.MyOrderViewModel;
import org.softuni.onlinegrocery.domain.models.view.OrderViewModel;
import org.softuni.onlinegrocery.service.OrderService;
import org.softuni.onlinegrocery.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrdersController extends BaseController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ModelMapper mapper;

    public OrdersController(
            ProductService productService,
            OrderService orderService,
            ModelMapper modelMapper){
        this.productService = productService;
        this.orderService = orderService;
        this.mapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<OrderViewModel> viewModels = orderService.findAllOrders()
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", viewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        OrderServiceModel order = this.orderService.findOrderById(id);
        modelAndView.addObject("order", this.mapper.map(order, OrderViewModel.class));

        return view("order/order-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        String customerName = principal.getName();
        List<OrderViewModel> myOrders = orderService.findOrdersByCustomer(customerName)
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        List<MyOrderViewModel> myPendingOrders = orderService.findOrdersByCustomerAndStatus(customerName, Status.Pending)
                .stream()
                .map(o -> mapper.map(o, MyOrderViewModel.class))
                .collect(Collectors.toList());

        List<MyOrderViewModel> myShippedOrders = orderService.findOrdersByCustomerAndStatus(customerName, Status.Shipped)
                .stream()
                .map(o -> mapper.map(o, MyOrderViewModel.class))
                .collect(Collectors.toList());

        List<MyOrderViewModel> myDeliveredOrders = orderService.findOrdersByCustomerAndStatus(customerName, Status.Delivered)
                .stream()
                .map(o -> mapper.map(o, MyOrderViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", myOrders);

        modelAndView.addObject("myPendingOrders", myPendingOrders);
        modelAndView.addObject("myShippedOrders", myShippedOrders);
        modelAndView.addObject("myDeliveredOrders", myDeliveredOrders);

        return view("order/my-orders", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("order", this.mapper.map(this.orderService.findOrderById(id), OrderViewModel.class));

        return view("order/order-details", modelAndView);
    }

    @GetMapping("/change/status/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderChangeStatus(@PathVariable String id) {
        this.orderService.changeOrderStatus(id);
        return redirect("/order/all");
    }


    @GetMapping("/fetch/{status}")
    @ResponseBody
    public List<OrderViewModel> fetchByCategory(@PathVariable String status) {
        if(status.equals("All")) {
            List<OrderViewModel> orders = this.orderService.findAllOrders()
                    .stream()
                    .map(order -> this.mapper.map(order, OrderViewModel.class))
                    .collect(Collectors.toList());
            return orders;
        }


        Status status1 = Status.Pending;
        switch (status){
            case "Shipped":
                status1 = Status.Shipped;
                break;
            case "Delivered":
                status1 = Status.Delivered;
                break;
            case "Acquired":
                status1 = Status.Acquired;
                break;
        }

        List<OrderViewModel> orders = this.orderService.findOrdersByStatus(status1)
                .stream()
                .map(order -> this.mapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
        return orders;
    }

    /*@GetMapping("/fetch")
    @ResponseBody
    public List<ProductAllViewModel> fetchAllProducts() {
        return this.productService.findAllProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());
    }*/
}
