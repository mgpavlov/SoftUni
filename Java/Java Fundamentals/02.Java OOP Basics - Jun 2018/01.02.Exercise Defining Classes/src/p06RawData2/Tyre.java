package p06RawData2;

public class Tyre {
    private double pressure;
    private int age;

    Tyre (double pressure, int age){
        this.pressure = pressure;
        this.age = age;
    }

    public double getPressure() {
        return this.pressure;
    }

    public int getAge() {
        return this.age;
    }
}
