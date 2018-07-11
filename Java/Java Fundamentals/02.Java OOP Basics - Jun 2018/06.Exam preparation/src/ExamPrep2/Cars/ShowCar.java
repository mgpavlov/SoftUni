package ExamPrep2.Cars;

public class ShowCar extends Car {
    private int stars;
    public ShowCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction,
                horsepower, acceleration, suspension, durability);
        this.stars = 0;
    }

    public int getStars() {
        return this.stars;
    }

    private void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(String.format("%d *", this.stars));
        return sb.toString();
    }

    @Override
    public void tuneCar(int tuneIndex, String addOn) {
        super.tuneCar(tuneIndex, addOn);
        this.setStars(this.getStars()+tuneIndex);
    }
}
