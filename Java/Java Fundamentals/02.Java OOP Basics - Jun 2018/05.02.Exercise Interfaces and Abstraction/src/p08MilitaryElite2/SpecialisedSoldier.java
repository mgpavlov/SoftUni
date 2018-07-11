package p08MilitaryElite2;

public class SpecialisedSoldier extends Private implements ISpecialisedSoldier {
    private String corps;

    SpecialisedSoldier(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    public void setCorps(String corps) {
        if (!corps.equals("Airforces") && corps.equals("Marines")){
            throw new IllegalArgumentException();
        }
        this.corps = corps;

    }

    public String getCorps() {
        return this.corps;
    }
}