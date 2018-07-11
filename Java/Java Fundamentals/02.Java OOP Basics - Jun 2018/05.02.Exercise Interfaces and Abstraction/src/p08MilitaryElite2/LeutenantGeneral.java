package p08MilitaryElite2;

import java.util.ArrayList;
import java.util.List;

public class LeutenantGeneral extends Private implements ILeutenantGeneral {
    List<Private> privates;

    LeutenantGeneral(String id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }
    void addPrivate(Private aPrivate){
        privates.add(aPrivate);
    }
    @Override
    public List<Private> getPrivates() {
        return privates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %s Salary: %.2f%nPrivates:%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary()));

        this.getPrivates().stream().sorted((a,b)-> b.getId().compareTo(a.getId())).forEach(
                p->{
                    sb.append(" ");
                    sb.append(p.toString());
                    sb.append(System.lineSeparator());
                }
        );
        return sb.toString();
    }
}