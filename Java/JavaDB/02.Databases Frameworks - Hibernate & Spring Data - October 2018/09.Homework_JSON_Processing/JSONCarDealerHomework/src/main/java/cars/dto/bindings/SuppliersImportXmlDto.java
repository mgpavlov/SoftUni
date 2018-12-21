package cars.dto.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersImportXmlDto {

    @XmlElement(name = "supplier")
    private List<SupplierImportDto> supplierImportDtos;

    public SuppliersImportXmlDto() {
    }

    public List<SupplierImportDto> getSupplierImportDtos() {
        return this.supplierImportDtos;
    }

    public void setSupplierImportDtos(List<SupplierImportDto> supplierImportDtos) {
        this.supplierImportDtos = supplierImportDtos;
    }
}
