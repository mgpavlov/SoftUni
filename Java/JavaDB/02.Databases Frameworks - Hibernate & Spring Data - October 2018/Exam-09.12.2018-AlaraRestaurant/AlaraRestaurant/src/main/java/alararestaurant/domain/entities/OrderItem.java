package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity(name = "order_items")
public class OrderItem extends BaseEntity {

    private Item item;
    private Integer quantity;
    private Order order;

    public OrderItem() {
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Item getItem() {
        return this.item;
    }

    @Column(name = "quantity", nullable = false)
    @Min(value = 1)
    public Integer getQuantity() {
        return this.quantity;
    }

    @ManyToOne
    public Order getOrder() {
        return this.order;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
