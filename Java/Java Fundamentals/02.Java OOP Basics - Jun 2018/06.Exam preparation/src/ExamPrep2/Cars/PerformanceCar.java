package ExamPrep2.Cars;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PerformanceCar extends Car {
    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.addOns = new ArrayList<>();
    }

    public Collection<String> getAddOns() {
        return Collections.unmodifiableList(this.addOns);
    }

    @Override
    public void setHorsepower(int horsepower) {
        super.setHorsepower(horsepower + ((horsepower*50 )/ 100));
    }
    @Override
    public void setSuspension(int suspension) {
        super.setSuspension(suspension - ((suspension*25) / 100));
    }
    @Override
    public void tuneCar(int tuneIndex, String addOn) {
        super.tuneCar(tuneIndex, addOn);
        this.addOns.add(addOn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.addOns.size() == 0){
            sb.append("Add-ons: None");
        }else {
            sb.append("Add-ons: ").append(String.join(", ", this.addOns));

        }
        return sb.toString();
    }
}
