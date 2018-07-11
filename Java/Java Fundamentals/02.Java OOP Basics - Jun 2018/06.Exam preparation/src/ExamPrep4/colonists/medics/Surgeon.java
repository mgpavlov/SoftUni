package ExamPrep4.colonists.medics;

public class Surgeon extends Medic {
    private static final int CLASS_BONUS = 2;
    public Surgeon(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    public int getPotential() {
        int ageBonus = 0;
        int signBonus = 0;
        if (super.getAge()>25 && super.getAge() < 35){
            ageBonus = 2;
        }
        switch (super.getSign()){
            case "precise":
                signBonus = 3;
                break;
            case "butcher":
                signBonus = -3;
                break;
        }

        int totalBonus = this.CLASS_BONUS + ageBonus + signBonus + super.getTalent();

        return totalBonus;
    }

    @Override
    public void grow(int years) {

    }
}
