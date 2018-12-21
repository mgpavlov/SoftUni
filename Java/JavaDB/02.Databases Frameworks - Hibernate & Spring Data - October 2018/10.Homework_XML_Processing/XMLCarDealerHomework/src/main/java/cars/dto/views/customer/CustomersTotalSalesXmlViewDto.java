package cars.dto.views.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersTotalSalesXmlViewDto {

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesViewDto> customerTotalSalesViewDtos;

    public CustomersTotalSalesXmlViewDto() {
    }

    public List<CustomerTotalSalesViewDto> getCustomerTotalSalesViewDtos() {
        return this.customerTotalSalesViewDtos;
    }

    public void setCustomerTotalSalesViewDtos(List<CustomerTotalSalesViewDto> customerTotalSalesViewDtos) {
        this.customerTotalSalesViewDtos = customerTotalSalesViewDtos;
    }
}
