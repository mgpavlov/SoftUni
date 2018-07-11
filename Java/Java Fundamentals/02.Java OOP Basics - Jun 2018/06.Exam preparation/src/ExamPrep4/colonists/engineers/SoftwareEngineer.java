package ExamPrep4.colonists.engineers;

public class SoftwareEngineer extends Engineer {
    private static final int CLASS_BONUS = 3;
    private static final int FLAT_BONUS = 2;
    public SoftwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }
    @Override
    public int getPotential() {
        int ageBonus = 0;
        if (super.getAge()>30){
            ageBonus = 2;
        }

        int totalBonus = this.CLASS_BONUS + ageBonus + super.getTalent() + this.FLAT_BONUS;

        return totalBonus;
    }

    @Override
    public void grow(int years) {

    }
}
