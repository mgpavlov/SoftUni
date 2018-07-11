package p03Ferrari;

public class Ferrari implements Car {
    private String name;
    private String model;
    private String driver;

    public Ferrari(String name, String model, String driver) {
        this.setName(name);
        this.setModel(model);
        this.setDriver(driver);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getDriver() {
        return this.driver;
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String pushGasPedal() {
        return "Zadu6avam sA!";
    }
}
