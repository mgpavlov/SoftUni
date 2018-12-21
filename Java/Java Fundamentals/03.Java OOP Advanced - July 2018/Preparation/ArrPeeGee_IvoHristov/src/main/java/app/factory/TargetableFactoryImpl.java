package app.factory;

import app.constants.TargetableNames;
import app.contracts.Targetable;
import app.contracts.TargetableFactory;
import app.models.participants.Boss;
import app.models.participants.Necromancer;
import app.models.participants.Warrior;
import app.models.participants.Wizard;

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
