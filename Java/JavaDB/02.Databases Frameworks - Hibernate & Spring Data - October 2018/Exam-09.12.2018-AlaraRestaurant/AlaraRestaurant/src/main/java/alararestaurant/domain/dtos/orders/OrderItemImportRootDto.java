package alararestaurant.domain.dtos.orders;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemImportRootDto {
    @XmlElement(name = "item")
    private OrderItemImportDto[] orderItemImportDtos;

    public OrderItemImportRootDto() {
    }

    public OrderItemImportDto[] getOrderItemImportDtos() {
        return this.orderItemImportDtos;
    }

    public void setOrderItemImportDtos(OrderItemImportDto[] orderItemImportDtos) {
        this.orderItemImportDtos = orderItemImportDtos;
    }
}
