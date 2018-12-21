package products.dto.bindings;

import com.google.gson.annotations.Expose;
import products.dto.utilities.CategoryIdDto;
import products.dto.utilities.UserIdDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Set;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDto {

    @Expose
    @XmlElement(name = "name")
    private String name;

    @Expose
    @XmlElement(name = "price")
    private BigDecimal price;

    private UserIdDto buyer;

    private UserIdDto seller;

    private Set<CategoryIdDto> categories;

    public ProductImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserIdDto getBuyer() {
        return this.buyer;
    }

    public void setBuyer(UserIdDto buyer) {
        this.buyer = buyer;
    }

    public UserIdDto getSeller() {
        return this.seller;
    }

    public void setSeller(UserIdDto seller) {
        this.seller = seller;
    }

    public Set<CategoryIdDto> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CategoryIdDto> categories) {
        this.categories = categories;
    }
}
