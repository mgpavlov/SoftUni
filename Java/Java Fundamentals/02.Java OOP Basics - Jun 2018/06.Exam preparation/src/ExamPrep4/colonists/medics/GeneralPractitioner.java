package ExamPrep4.colonists.medics;

import ExamPrep4.colonists.medics.Medic;

public class GeneralPractitioner extends Medic {
    private static final int CLASS_BONUS = 2;
    public GeneralPractitioner(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    public int getPotential() {
        int ageBonus = 0;
        int signBonus = 0;
        if (super.getAge()>15){
            ageBonus = 1;
        }
        switch (super.getSign()){
            case "caring":
                signBonus = 1;
                break;
            case "careless":
                signBonus = -2;
                break;
        }

        int totalBonus = this.CLASS_BONUS + ageBonus + signBonus + super.getTalent();

        return totalBonus;
    }

    @Override
    public void grow(int years) {

    }
}
