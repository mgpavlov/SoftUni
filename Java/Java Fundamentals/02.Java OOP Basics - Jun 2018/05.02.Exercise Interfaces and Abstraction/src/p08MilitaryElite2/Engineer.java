package p08MilitaryElite2;

import java.util.ArrayList;
import java.util.List;

public class Engineer extends SpecialisedSoldier implements IEngineer {
    private List<Repair> repairs;

    public Engineer(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();

    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    public void addRepair(Repair repair){
        this.getRepairs().add(repair);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %s Salary: %.2f%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary()));
        sb.append("Corps: ").append(this.getCorps()).append(System.lineSeparator()).append("Repairs:").append(System.lineSeparator());
        for (Repair repair : this.getRepairs()) {
            sb.append(repair.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}