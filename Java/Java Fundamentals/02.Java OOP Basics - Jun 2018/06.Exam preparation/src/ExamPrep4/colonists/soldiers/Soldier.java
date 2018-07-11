package ExamPrep4.colonists.soldiers;

import ExamPrep4.colonists.Colonist;

public class Soldier extends Colonist {
    private static final int CLASS_BONUS = 3;
    private static final int AGE_BONUS = 3;
    public Soldier(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    public int getPotential() {
        return this.CLASS_BONUS + this.AGE_BONUS + super.getTalent();
    }

    @Override
    public void grow(int years) {

    }
}
