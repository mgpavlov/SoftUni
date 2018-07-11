package p08MilitaryElite.interfaces;

import java.util.Collection;

public interface Commando extends SpecialisedSoldier {
    Collection<Mission> getMissions();

    void addMission(Mission mission);
}
