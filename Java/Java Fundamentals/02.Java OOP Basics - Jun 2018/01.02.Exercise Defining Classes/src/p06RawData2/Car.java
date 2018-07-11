package p06RawData2;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tyre[] tyres;

    Car(String model, Engine engine, Cargo cargo, Tyre[] tyres) {
        this.setModel(model);
        this.setEngine(engine);
        this.setCargo(cargo);
        this.setTyres(tyres);
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setEngine(Engine engine) {
        this.engine = engine;
    }

    private void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    private void setTyres(Tyre[] tyres) {
        this.tyres = tyres;
    }

    public String getModel() {
        return model;
    }

    public Cargo getCargo() {
        return cargo;
    }
    public boolean checkTyresPressure() {
        for (Tyre tyre : tyres) {
            if (tyre.getPressure() < 1) {
                return true;
            }
        }
        return false;
    }

    public Engine getEngine() {
        return engine;
    }
}
