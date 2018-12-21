package alararestaurant.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order extends BaseEntity {

    private String customer;
    private LocalDateTime dateTime;
    private OrderType type;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    @Column(name = "customer", nullable = false, columnDefinition = "TEXT")
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "type", nullable = false, columnDefinition = "ENUM('ForHere', 'ToGo') default 'ForHere'")
    @Enumerated(value = EnumType.STRING)
    public OrderType getType() {
        return this.type;
    }//todo? String

    public void setType(OrderType type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
