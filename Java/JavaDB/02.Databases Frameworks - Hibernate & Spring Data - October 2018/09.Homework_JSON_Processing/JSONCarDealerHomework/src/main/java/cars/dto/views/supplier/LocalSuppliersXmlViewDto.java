package cars.dto.views.supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSuppliersXmlViewDto {

    @XmlElement(name = "supplier")
    private List<LocalSupplierViewDto> localSupplierViewDtos;

    public LocalSuppliersXmlViewDto() {
    }

    public List<LocalSupplierViewDto> getLocalSupplierViewDtos() {
        return this.localSupplierViewDtos;
    }

    public void setLocalSupplierViewDtos(List<LocalSupplierViewDto> localSupplierViewDtos) {
        this.localSupplierViewDtos = localSupplierViewDtos;
    }
}
