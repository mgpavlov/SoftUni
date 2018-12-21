package app.factory;

import app.constants.Texts;
import app.contracts.SpecialityFactory;
import app.contracts.Specialty;
import app.models.specialties.Heal;
import app.models.specialties.Swiftness;
import app.models.specialties.Toughness;

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
