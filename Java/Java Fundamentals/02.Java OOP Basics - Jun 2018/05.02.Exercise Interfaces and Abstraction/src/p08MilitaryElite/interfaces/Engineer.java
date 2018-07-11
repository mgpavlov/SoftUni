package p08MilitaryElite.interfaces;


import java.util.Collection;

public interface Engineer extends SpecialisedSoldier {
    Collection<Repair> getRepairs();

    void addRepair(Repair repair);
}
