package callofduty.core;

import callofduty.domain.missions.EscortMission;
import callofduty.domain.missions.HuntMission;
import callofduty.domain.missions.SurveillanceMission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class MissionControlImplTest {

    @Test
    public void generateMission1() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MissionControl missionControl = new MissionControlImpl();
        EscortMission escortMission = (EscortMission) missionControl.generateMission("a", 1.0, 1.0);
        Assert.assertEquals(escortMission.getRating(), 0.75, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getBounty(),1.25, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getId(), "a");

        HuntMission huntMission = (HuntMission) missionControl.generateMission("b", 1.0, 1.0);
        Assert.assertEquals(huntMission.getRating(), 1.5, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getBounty(),2.0, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getId(), "b");

        SurveillanceMission surveillanceMission = (SurveillanceMission) missionControl.generateMission("c", 1.0, 1.0);
        Assert.assertEquals(surveillanceMission.getRating(), 0.25, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getBounty(),1.50, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getId(), "c");

    }

    @Test
    public void generateMission2() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MissionControl missionControl = new MissionControlImpl();
        EscortMission escortMission = (EscortMission) missionControl.generateMission("a", 1000.0, 1000000.0);
        Assert.assertEquals(escortMission.getRating(), 75.0, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getBounty(),125000, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getId(), "a");

        HuntMission huntMission = (HuntMission) missionControl.generateMission("b", 1000.0, 1000000.0);
        Assert.assertEquals(huntMission.getRating(), 150, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getBounty(),200000, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getId(), "b");

        SurveillanceMission surveillanceMission = (SurveillanceMission) missionControl.generateMission("c", 1000.0, 1000000.0);
        Assert.assertEquals(surveillanceMission.getRating(), 25, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getBounty(),150000, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getId(), "c");

    }

    @Test
    public void generateMission3() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MissionControl missionControl = new MissionControlImpl();
        EscortMission escortMission = (EscortMission) missionControl.generateMission("123456789", 1000.0, 1000000.0);
        Assert.assertEquals(escortMission.getRating(), 75.0, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getBounty(),125000, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getId(), "12345678");

        HuntMission huntMission = (HuntMission) missionControl.generateMission("b12345678", 1000.0, 1000000.0);
        Assert.assertEquals(huntMission.getRating(), 150, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getBounty(),200000, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getId(), "b1234567");

        SurveillanceMission surveillanceMission = (SurveillanceMission) missionControl.generateMission("c12345678", 1000.0, 1000000.0);
        Assert.assertEquals(surveillanceMission.getRating(), 25, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getBounty(),150000, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getId(), "c1234567");
    }

    @Test
    public void generateMission4() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MissionControl missionControl = new MissionControlImpl();
        EscortMission escortMission = (EscortMission) missionControl.generateMission("123456789", -1.0, -1000000.0);
        Assert.assertEquals(escortMission.getRating(), 0, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getBounty(),0, Double.MIN_VALUE);
        Assert.assertEquals(escortMission.getId(), "12345678");

        HuntMission huntMission = (HuntMission) missionControl.generateMission("b12345678", -1000.0, -1000000.0);
        Assert.assertEquals(huntMission.getRating(), 0, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getBounty(),0, Double.MIN_VALUE);
        Assert.assertEquals(huntMission.getId(), "b1234567");

        SurveillanceMission surveillanceMission = (SurveillanceMission) missionControl.generateMission("c12345678", -1000.0, -1000000.0);
        Assert.assertEquals(surveillanceMission.getRating(), 0, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getBounty(),0, Double.MIN_VALUE);
        Assert.assertEquals(surveillanceMission.getId(), "c1234567");
    }

}