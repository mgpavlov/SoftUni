package alararestaurant.domain.dtos.orders;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto {

    @XmlElement(name = "customer")
    private String customer;

    @XmlElement(name = "employee")
    private String employee;

    @XmlElement(name = "date-time")
    private String dateTime;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "items")
    private OrderItemImportRootDto orderItemImportRootDto;

    public OrderImportDto() {
    }


    @NotNull
    public String getCustomer() {
        return this.customer;
    }

    @NotNull
    public String getEmployee() {
        return this.employee;
    }

    @NotNull
    public String getDateTime() {
        return this.dateTime;
    }

    @NotNull
    public String getType() {
        return this.type;
    }

    public OrderItemImportRootDto getOrderItemImportRootDto() {
        return this.orderItemImportRootDto;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrderItemImportRootDto(OrderItemImportRootDto orderItemImportRootDto) {
        this.orderItemImportRootDto = orderItemImportRootDto;
    }
}
