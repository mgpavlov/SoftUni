package app.contracts;

public interface SuperHero extends Hero, SpecialtyBonuses {

    void addSpecialty(Specialty specialty);
}
