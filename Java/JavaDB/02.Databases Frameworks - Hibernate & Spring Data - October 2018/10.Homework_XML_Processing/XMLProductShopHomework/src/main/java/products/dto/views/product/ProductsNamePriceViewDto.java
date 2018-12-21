package products.dto.views.product;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsNamePriceViewDto {

    @Expose
    @XmlAttribute
    private int count;

    @Expose
    @XmlElement(name = "product")
    private Set<ProductNamePriceViewDto> products;

    public ProductsNamePriceViewDto() {
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductNamePriceViewDto> getProducts() {
        return this.products;
    }

    public void setProducts(Set<ProductNamePriceViewDto> products) {
        this.products = products;
        this.setCount(products.size());
    }
}
