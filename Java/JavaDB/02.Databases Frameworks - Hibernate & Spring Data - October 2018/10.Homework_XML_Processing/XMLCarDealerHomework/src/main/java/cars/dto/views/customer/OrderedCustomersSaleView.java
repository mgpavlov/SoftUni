package cars.dto.views.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderedCustomersSaleView {

    @Expose
    private double discount;

    @Expose
    @SerializedName("customer")
    private String customerName;

    @Expose
    @SerializedName("car")
    private String carMake;

    public OrderedCustomersSaleView() {
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarMake() {
        return this.carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }
}
