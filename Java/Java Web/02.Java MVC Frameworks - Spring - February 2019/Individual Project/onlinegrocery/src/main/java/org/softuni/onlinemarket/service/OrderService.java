package org.softuni.onlinemarket.service;

import org.softuni.onlinemarket.domain.entities.enumeration.Status;
import org.softuni.onlinemarket.domain.models.service.OrderServiceModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);

    List<OrderServiceModel> findOrdersByStatus(Status status);

    void changeOrderStatus(String id);

    List<OrderServiceModel> findOrdersByCustomerAndStatus(String customerName, Status status);
}
