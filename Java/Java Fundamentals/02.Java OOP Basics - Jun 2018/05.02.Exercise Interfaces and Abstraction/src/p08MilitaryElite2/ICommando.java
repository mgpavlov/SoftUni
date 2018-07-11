package p08MilitaryElite2;
import java.util.List;

public interface ICommando extends ISpecialisedSoldier {
    List<Mission> getMissions();
    void CompleteMission();

}
