package products.dto.views.user;

import com.google.gson.annotations.Expose;
import products.dto.views.product.ProductsNamePriceViewDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsViewDto {

    @XmlTransient
    private long id;

    @Expose
    @XmlAttribute(name = "first-name")
    private String firstName;

    @Expose
    @XmlAttribute(name = "last-name")
    private String lastName;

    @Expose
    @XmlAttribute
    private int age;

    @Expose
    @XmlElement(name = "sold-products")
    private ProductsNamePriceViewDto soldProducts;

    public UserSoldProductsViewDto() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductsNamePriceViewDto getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(ProductsNamePriceViewDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
