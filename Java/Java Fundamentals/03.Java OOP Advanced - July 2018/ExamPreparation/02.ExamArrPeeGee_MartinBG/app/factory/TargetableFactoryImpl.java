package ExamArrPeeGee.app.factory;

import ExamArrPeeGee.app.constants.TargetableNames;
import ExamArrPeeGee.app.contracts.Targetable;
import ExamArrPeeGee.app.contracts.TargetableFactory;
import ExamArrPeeGee.app.models.participants.Boss;
import ExamArrPeeGee.app.models.participants.Necromancer;
import ExamArrPeeGee.app.models.participants.Warrior;
import ExamArrPeeGee.app.models.participants.Wizard;

public class TargetableFactoryImpl implements TargetableFactory {

    @Override
    public Targetable create(String name, String className) throws
            ClassNotFoundException {
        switch (className) {
        case TargetableNames.TARGETABLE_BOSS:
            return new Boss(name);
        case TargetableNames.TARGETABLE_WIZARD:
            return new Wizard(name);
        case TargetableNames.TARGETABLE_WARRIOR:
            return new Warrior(name);
        case TargetableNames.TARGETABLE_NECROMANCER:
            return new Necromancer(name);
        default:
            throw new ClassNotFoundException();
        }
    }
}
