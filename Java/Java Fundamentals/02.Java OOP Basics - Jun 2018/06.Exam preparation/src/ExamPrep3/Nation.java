package ExamPrep3;

import ExamPrep3.benders.Bender;
import ExamPrep3.monuments.Monument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nation {
    private List<Bender> benders;
    private List<Monument> monuments;
    private String name;

    public Nation(String name) {
        this.name = name;
        this.benders = new ArrayList<>();
        this.monuments = new ArrayList<>();
    }

    public List<Bender> getBenders() {
        return Collections.unmodifiableList(this.benders);
    }

    public List<Monument> getMonuments() {
        return Collections.unmodifiableList(this.monuments);
    }

    protected void setBenders(List<Bender> benders) {
        this.benders = benders;
    }

    protected void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }

    public double getTotalPower(){
        double benderTotalPower = this.benders.stream().mapToDouble(b->b.getPower()).sum();
        double affinityTotal = this.monuments.stream().mapToDouble(m->m.getAffinity()).sum();
        double totalPower = benderTotalPower + (benderTotalPower/100)*affinityTotal;
        return totalPower;
    }

    public String getName() {
        return this.name;
    }

    public void addBenders(Bender bender){
        benders.add(bender);
    }

    public void addMonument (Monument monument){
        monuments.add(monument);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.name);
        result.append(" Nation").append(System.lineSeparator())
                .append("Benders:");
        if (this.benders.isEmpty()){
            result.append(" None").append(System.lineSeparator());
        }else {
            result.append(System.lineSeparator());
            this.benders.forEach(b-> result.append("###").append(b.toString()));
        }
        result.append("Monuments:");
        if (this.monuments.isEmpty()){
            result.append(" None").append(System.lineSeparator());
        }else {
            result.append(System.lineSeparator());
            this.monuments.forEach(m-> result.append("###").append(m.toString()));
        }
        return result.toString().trim();
    }
}
