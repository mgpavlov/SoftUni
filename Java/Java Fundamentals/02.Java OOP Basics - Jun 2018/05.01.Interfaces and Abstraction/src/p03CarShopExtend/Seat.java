package p03CarShopExtend;

public class Seat implements Sellable {
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;
    private Double price;



    Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {

        this.setModel(model);
        this.setColor(color);
        this.setHorsePower(horsePower);
        this.setCountryProduced(countryProduced);
        this.setPrice(price);
    }
    private String getCountryProduced() {
        return this.countryProduced;
    }

    private void setCountryProduced(String countryProduced) {
        this.countryProduced = countryProduced;
    }

    private void setModel(String model) {
        this.model = model;
    }
    private void setColor(String color) {
        this.color = color;
    }

    private void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }
    private void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                this.getModel(), this.getCountryProduced(), Car.TIRES);
    }
}
