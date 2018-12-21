package products.dto.views.product;

import com.google.gson.annotations.Expose;
import products.dto.views.user.UserNameViewDto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewDto {

    @Expose
    @XmlAttribute(name = "name")
    private String name;

    @Expose
    @XmlAttribute(name = "price")
    private BigDecimal price;

    @XmlTransient
    private UserNameViewDto seller;

    @XmlAttribute(name = "seller")
    private String sellerFullName;

    public ProductViewDto() {
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

    public UserNameViewDto getSeller() {
        return this.seller;
    }

    public void setSeller(UserNameViewDto seller) {
        this.seller = seller;
        this.setSellerFullName(seller.getFirstName() + " " + seller.getLastName());
    }

    public String getSellerFullName() {
        return this.sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }
}
