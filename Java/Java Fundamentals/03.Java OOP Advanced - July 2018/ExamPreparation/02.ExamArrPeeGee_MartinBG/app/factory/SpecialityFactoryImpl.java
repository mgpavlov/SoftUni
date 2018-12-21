package ExamArrPeeGee.app.factory;

import ExamArrPeeGee.app.constants.Texts;
import ExamArrPeeGee.app.contracts.SpecialityFactory;
import ExamArrPeeGee.app.contracts.Specialty;
import ExamArrPeeGee.app.models.specialties.Heal;
import ExamArrPeeGee.app.models.specialties.Swiftness;
import ExamArrPeeGee.app.models.specialties.Toughness;

public class SpecialityFactoryImpl implements SpecialityFactory {

    @Override
    public Specialty create(String speciality) throws ClassNotFoundException {
        switch (speciality) {
        case Texts.SPECIALTY_HEAL:
            return new Heal();
        case Texts.SPECIALTY_TOUGHNESS:
            return new Toughness();
        case Texts.SPECIALTY_SWIFTNESS:
            return new Swiftness();
        default:
            throw new ClassNotFoundException();
        }
    }
}
