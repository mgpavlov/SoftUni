package org.softuni.onlinemarket.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.Order;
import org.softuni.onlinemarket.domain.entities.enumeration.Status;
import org.softuni.onlinemarket.domain.models.service.OrderServiceModel;
import org.softuni.onlinemarket.error.OrderNotFoundException;
import org.softuni.onlinemarket.repository.OrderRepository;
import org.softuni.onlinemarket.repository.ProductRepository;
import org.softuni.onlinemarket.validation.ProductValidationService;
import org.softuni.onlinemarket.validation.UserValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidationService userValidation;
    private final ProductValidationService productValidation;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserService userService,
            UserValidationService userValidation,
            ProductValidationService productValidation,
            ModelMapper modelMapper
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.userValidation = userValidation;
        this.productValidation = productValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(OrderServiceModel orderServiceModel) {
        orderServiceModel.setFinishedOn(LocalDateTime.now());
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
        this.orderRepository.save(order);
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String username) {
        return this.orderRepository.findAllOrdersByCustomer_UsernameOrderByFinishedOn(username)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel findOrderById(String id) {
        return this.orderRepository.findById(id)
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .orElseThrow(() -> new OrderNotFoundException("Nqma Go"));
    }

    @Override
    public List<OrderServiceModel> findOrdersByStatus(Status status) {
        return this.orderRepository.findAllOrdersByStatus_OrderByFinishedOn(status)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changeOrderStatus(String id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        this.changeStatus(order);
        this.changeDeliveryDate(order);
        this.orderRepository.save(order);
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomerAndStatus(String customerName, Status status) {
        return this.orderRepository.findAllOrdersByCustomerUsernameAndStatus_OrderByFinishedOn(customerName, status)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }


    private void changeStatus(Order order) {
        order
                .setStatus(Status.values()[Arrays.asList(Status.values()).indexOf(order.getStatus()) + 1]);
    }

    private void changeDeliveryDate(Order order) {
        long days = (System.currentTimeMillis() % 21) + 20;
        order.setFinishedOn(LocalDateTime.now().plusDays(days));
    }
}