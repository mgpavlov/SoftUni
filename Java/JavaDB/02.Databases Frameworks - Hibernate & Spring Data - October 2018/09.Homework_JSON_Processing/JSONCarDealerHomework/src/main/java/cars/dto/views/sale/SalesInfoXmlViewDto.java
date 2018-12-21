package cars.dto.views.sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesInfoXmlViewDto {

    @XmlElement(name = "sale")
    private List<SaleInfoViewDto> saleInfoViewDtos;

    public SalesInfoXmlViewDto() {
    }

    public List<SaleInfoViewDto> getSaleInfoViewDtos() {
        return this.saleInfoViewDtos;
    }

    public void setSaleInfoViewDtos(List<SaleInfoViewDto> saleInfoViewDtos) {
        this.saleInfoViewDtos = saleInfoViewDtos;
    }
}
