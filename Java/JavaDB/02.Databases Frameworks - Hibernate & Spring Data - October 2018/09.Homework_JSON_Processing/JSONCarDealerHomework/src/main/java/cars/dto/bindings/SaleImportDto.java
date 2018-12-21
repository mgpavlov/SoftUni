package cars.dto.bindings;

import cars.dto.utilities.CarIdDto;
import cars.dto.utilities.CustomerIdYoungDriverDto;
import com.google.gson.annotations.Expose;

public class SaleImportDto {

    @Expose
    private double discount;

    @Expose
    private CustomerIdYoungDriverDto customer;

    @Expose
    private CarIdDto car;

    public SaleImportDto() {
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public CustomerIdYoungDriverDto getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerIdYoungDriverDto customer) {
        this.customer = customer;
    }

    public CarIdDto getCar() {
        return this.car;
    }

    public void setCar(CarIdDto car) {
        this.car = car;
    }
}
