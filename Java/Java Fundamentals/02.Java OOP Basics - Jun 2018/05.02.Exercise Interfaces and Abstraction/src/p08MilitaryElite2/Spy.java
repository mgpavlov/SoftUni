package p08MilitaryElite2;

public class Spy extends Soldier implements ISpy {

    private String code;

    Spy(String id, String firstName, String lastName, String code) {
        super(id, firstName, lastName);
        this.code = code;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %s%nCode Number: %s%n", this.getFirstName(), this.getLastName(), this.getId(), this.getCode());
    }
}
