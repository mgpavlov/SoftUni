package ExamPrep4.colonists.engineers;

public class HardwareEngineer extends Engineer {
    private static final int CLASS_BONUS = 3;
    public HardwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }
    @Override
    public int getPotential() {
        int ageBonus = 0;
        if (super.getAge()>30 || super.getAge()<18){
            ageBonus = 2;
        }

        int totalBonus = this.CLASS_BONUS + ageBonus + super.getTalent();

        return totalBonus;
    }

    @Override
    public void grow(int years) {

    }
}
