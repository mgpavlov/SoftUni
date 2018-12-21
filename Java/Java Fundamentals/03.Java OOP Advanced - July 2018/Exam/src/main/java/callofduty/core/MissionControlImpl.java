package callofduty.core;

import callofduty.domain.missions.AbstractMission;
import callofduty.domain.missions.EscortMission;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissionControlImpl implements MissionControl {
    private static final int MISSION_ID_MAXIMUM_LENGTH = 8;

    private static final double MISSION_RATING_MINIMUM_VALUE = 0.00;

    private static final double MISSION_RATING_MAXIMUM_VALUE = 100.00;

    private static final double MISSION_BOUNTY_MINIMUM_VALUE = 0.00;

    private static final double MISSION_BOUNTY_MAXIMUM_VALUE = 100000.0;

    private Map<String, Class> missionClasses;

    private Iterator<Map.Entry<String, Class>> missionIterator;

    public MissionControlImpl() {
        this.initMissionClasses();
        this.missionIterator = this.missionClasses
                .entrySet()
                .iterator();
    }

    private void initMissionClasses() {
        try {
            this.missionClasses = new LinkedHashMap<>() {{
                put("EscortMission", Class.forName("callofduty.domain.missions.EscortMission"));
                put("HuntMission", Class.forName("callofduty.domain.missions.HuntMission"));
                put("SurveillanceMission", Class.forName("callofduty.domain.missions.SurveillanceMission"));
            }};
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkAndReformMissionId(String missionId) {
        //todo if id =""
        return missionId.length() > MISSION_ID_MAXIMUM_LENGTH
                ? missionId.substring(0, MISSION_ID_MAXIMUM_LENGTH) //todo -1?;
                : missionId;
    }

    private Double checkAndReformMissionRating(double missionRating) {
        return missionRating < MISSION_RATING_MINIMUM_VALUE
                ? MISSION_RATING_MINIMUM_VALUE
                : (missionRating > MISSION_RATING_MAXIMUM_VALUE
                ? MISSION_RATING_MAXIMUM_VALUE
                : missionRating);
    }

    private Double checkAndreformMissionBounty(double missionBounty) {
        return missionBounty < MISSION_BOUNTY_MINIMUM_VALUE
                ? MISSION_BOUNTY_MINIMUM_VALUE
                : (missionBounty > MISSION_BOUNTY_MAXIMUM_VALUE
                ? MISSION_BOUNTY_MAXIMUM_VALUE
                : missionBounty);
    }

    private void updateMissionType() {
        this.missionIterator = this
                .missionClasses
                .entrySet()
                .iterator();
    }

    private Class currentMission() {
        if (!this.missionIterator.hasNext()) {
            this.updateMissionType();

        }
        return this.missionIterator.next().getValue();
    }

    private Mission instantiateMissionObject(String missionId, double missionRating, double missionBounty) {
        Mission missionObject = null;

        try {
            missionObject = (Mission)
                    this.currentMission()
                            .getConstructor(String.class, double.class, double.class)
                            .newInstance(missionId, missionRating, missionBounty);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ignored) {

        }

        return missionObject;
    }

    @Override
    public Mission generateMission(String missionId, Double missionRating, Double missionBounty) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        missionId = this.checkAndReformMissionId(missionId);
        missionRating = this.checkAndReformMissionRating(missionRating);
        missionBounty = this.checkAndreformMissionBounty(missionBounty);

        Mission generatedMission =
                this.instantiateMissionObject(missionId, missionRating, missionBounty);

        return generatedMission;
    }

}