package products.dto.views.category;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductsCountViewDto {

    @Expose
    @XmlAttribute(name = "name")
    private String category;

    @Expose
    @XmlElement(name = "products-count")
    private BigInteger count;

    @Expose
    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;

    @Expose
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryByProductsCountViewDto() {
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigInteger getCount() {
        return this.count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public BigDecimal getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
