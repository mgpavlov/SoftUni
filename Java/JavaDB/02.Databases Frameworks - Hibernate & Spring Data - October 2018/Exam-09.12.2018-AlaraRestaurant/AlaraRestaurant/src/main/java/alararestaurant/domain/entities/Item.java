package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "items")
public class Item extends BaseEntity {

    private String name;
    private Category category;
    private BigDecimal price;
    private List<OrderItem> orderItems;

    public Item() {
        this.orderItems = new ArrayList<>();
    }

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Category getCategory() {
        return this.category;
    }

    @Column(name = "price", nullable = false)
    @DecimalMin("0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    @OneToMany(mappedBy = "item")
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
