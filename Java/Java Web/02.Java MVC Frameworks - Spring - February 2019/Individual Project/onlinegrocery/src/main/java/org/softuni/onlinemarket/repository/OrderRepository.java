package org.softuni.onlinemarket.repository;

import org.softuni.onlinemarket.domain.entities.Order;
import org.softuni.onlinemarket.domain.entities.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllOrdersByCustomer_UsernameOrderByFinishedOn(String username);

    List<Order> findAllOrdersByStatus_OrderByFinishedOn(Status status);
}
