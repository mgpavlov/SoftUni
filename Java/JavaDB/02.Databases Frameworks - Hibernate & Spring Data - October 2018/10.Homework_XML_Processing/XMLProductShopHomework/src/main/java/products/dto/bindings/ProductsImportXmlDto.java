package products.dto.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportXmlDto {

    @XmlElement(name = "product")
    private List<ProductImportDto> productImportDtos;

    public ProductsImportXmlDto() {
    }

    public List<ProductImportDto> getProductImportDtos() {
        return this.productImportDtos;
    }

    public void setProductImportDtos(List<ProductImportDto> productImportDtos) {
        this.productImportDtos = productImportDtos;
    }
}
