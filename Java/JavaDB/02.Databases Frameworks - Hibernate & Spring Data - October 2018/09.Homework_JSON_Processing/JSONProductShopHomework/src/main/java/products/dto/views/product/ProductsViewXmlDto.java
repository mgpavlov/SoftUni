package products.dto.views.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsViewXmlDto {

    @XmlElement(name = "product")
    private List<ProductViewDto> productViewDtos;

    public ProductsViewXmlDto() {
    }

    public List<ProductViewDto> getProductViewDtos() {
        return this.productViewDtos;
    }

    public void setProductViewDtos(List<ProductViewDto> productViewDtos) {
        this.productViewDtos = productViewDtos;
    }
}
