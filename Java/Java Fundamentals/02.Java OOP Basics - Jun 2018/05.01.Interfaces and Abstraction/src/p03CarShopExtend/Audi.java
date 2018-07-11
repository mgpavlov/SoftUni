package p03CarShopExtend;

public class Audi implements Rentable {
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;
    private Integer minRentDay;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced, Integer minRentDay, Double pricePerDay) {
        this.setModel(model);
        this.setColor(color);
        this.setHorsePower(horsePower);
        this.setCountryProduced(countryProduced);
        this.setMinRentDay(minRentDay);
        this.setPricePerDay(pricePerDay);
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

    private void setCountryProduced(String countryProduced) {
        this.countryProduced = countryProduced;
    }

    private void setMinRentDay(Integer minRentDay) {
        this.minRentDay = minRentDay;
    }

    private void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    private String getCountryProduced() {
        return this.countryProduced;
    }

    @Override
    public int getMinRentDay() {
        return this.minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
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
    public String toString() {
        return "This is " + getModel() + " produced in " + getCountryProduced() + " and have " + TIRES + " tires";
    }
}
