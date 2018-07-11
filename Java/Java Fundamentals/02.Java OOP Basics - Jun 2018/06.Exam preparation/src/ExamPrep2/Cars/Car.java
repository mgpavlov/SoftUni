package ExamPrep2.Cars;

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;

    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;

    protected Car(String brand, String model, int yearOfProduction,
                  int horsepower, int acceleration, int suspension, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.setHorsepower(horsepower);
        this.acceleration = acceleration;
        this.setSuspension(suspension);
        this.durability = durability;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public int getYearOfProduction() {
        return this.yearOfProduction;
    }

    public int getAcceleration() {
        return this.acceleration;
    }

    public int getDurability() {
        return this.durability;
    }

    protected void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    protected void setSuspension(int suspension) {
        this.suspension = suspension;
    }


    public int getEnginePerformance(){
        return this.horsepower/this.acceleration;
    }
    public int getSuspensionPerformance(){
        return this.suspension+this.durability;
    }
    public int getOverallPerformance(){
        return this.getSuspensionPerformance()+this.getEnginePerformance();
    }
    public int getTimePerformance(){
        return (this.horsepower / 100) * this.acceleration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %d\r\n%d HP, 100 m/h in %d s\r\n%d Suspension force, %d Durability\r\n"
                ,this.brand, this.model, this.yearOfProduction, this.horsepower, this.acceleration, this.suspension, this.durability));
        return sb.toString();
    }

    public void tuneCar(int tuneIndex, String addOn){
        this.horsepower += tuneIndex;
        this.suspension += tuneIndex/2;
    };

    protected int getHorsepower() {
        return this.horsepower;
    }

    protected int getSuspension() {
        return this.suspension;
    }

    public void decreasesDurability(int decreaseParam) {
        this.durability -= decreaseParam;
    }
}
