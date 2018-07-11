package p02CarShop;

public class Seat implements Car {
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduces;

    Seat(String model, String color, Integer horsePower, String countryProduces) {
        this.setModel(model);
        this.setColor(color);
        this.setHorsePower(horsePower);
        this.setCountryProduces(countryProduces);
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

    private void setCountryProduces(String countryProduces) {
        this.countryProduces = countryProduces;
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

    private String getCountryProduces() {
        return this.countryProduces;
    }

    @Override
    public String toString() {
        return "This is " + getModel() + " produced in " + getCountryProduces() + " and have " + TIRES + " tires";
    }
}
