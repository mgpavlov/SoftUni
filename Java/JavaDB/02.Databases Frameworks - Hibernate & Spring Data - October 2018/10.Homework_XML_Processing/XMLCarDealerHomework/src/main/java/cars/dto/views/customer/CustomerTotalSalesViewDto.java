package cars.dto.views.customer;

import cars.dto.utilities.PartPriceUtilityDto;
import cars.dto.utilities.SaleCarPartsUtilityDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerTotalSalesViewDto {

    @Expose
    @SerializedName("fullName")
    @XmlAttribute(name = "full-name")
    private String name;

    @Expose
    @XmlAttribute(name = "bought-cars")
    private int boughtCars;

    @XmlTransient
    private Set<SaleCarPartsUtilityDto> sales;

    @Expose
    @XmlAttribute(name = "spent-money")
    private double spentMoney;

    public CustomerTotalSalesViewDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SaleCarPartsUtilityDto> getSales() {
        return this.sales;
    }

    public void setSales(Set<SaleCarPartsUtilityDto> sales) {
        this.sales = sales;
        this.setBoughtCars(sales.size());

        double spentMoney = sales.stream().mapToDouble(x -> x.getCarParts().stream().mapToDouble(PartPriceUtilityDto::getPrice).sum()).sum();
        this.setSpentMoney(spentMoney);
    }

    public int getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public double getSpentMoney() {
        return this.spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
