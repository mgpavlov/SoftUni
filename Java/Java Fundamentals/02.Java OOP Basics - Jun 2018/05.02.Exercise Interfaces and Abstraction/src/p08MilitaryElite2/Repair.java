package p08MilitaryElite2;

public class Repair {
    private String part;
    private String hours;

    Repair(String part, String hours) {
        this.part = part;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return String.format("  Part Name: %s Hours Worked: %s", this.part, this.hours);
    }
}
