package products.dto.views.user;

import com.google.gson.annotations.Expose;
import products.dto.views.product.ProductWithBuyerViewDto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNameSoldProductsViewDto {

    @Expose
    @XmlAttribute(name = "first-name")
    private String firstName;

    @Expose
    @XmlAttribute(name = "last-name")
    private String lastName;

    @Expose
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<ProductWithBuyerViewDto> soldProducts;

    public UserNameSoldProductsViewDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductWithBuyerViewDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductWithBuyerViewDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
