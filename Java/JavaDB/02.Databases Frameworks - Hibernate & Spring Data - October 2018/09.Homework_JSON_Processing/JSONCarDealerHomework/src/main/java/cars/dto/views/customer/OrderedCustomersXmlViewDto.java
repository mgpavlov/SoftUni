package cars.dto.views.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersXmlViewDto {

    @XmlElement(name = "customer")
    private List<OrderedCustomersViewDto> orderedCustomersViewDtos;

    public OrderedCustomersXmlViewDto() {
    }

    public List<OrderedCustomersViewDto> getOrderedCustomersViewDtos() {
        return this.orderedCustomersViewDtos;
    }

    public void setOrderedCustomersViewDtos(List<OrderedCustomersViewDto> orderedCustomersViewDtos) {
        this.orderedCustomersViewDtos = orderedCustomersViewDtos;
    }
}
