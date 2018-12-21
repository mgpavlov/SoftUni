package callofduty.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface MissionControl {
    Mission generateMission(String missionId, Double missionRating, Double missionBounty) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
