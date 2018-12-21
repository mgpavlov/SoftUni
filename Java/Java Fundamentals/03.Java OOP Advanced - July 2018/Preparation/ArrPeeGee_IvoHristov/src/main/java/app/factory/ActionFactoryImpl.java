package app.factory;

import app.constants.ActionNames;
import app.contracts.Action;
import app.contracts.ActionFactory;
import app.models.actions.BossFight;
import app.models.actions.OneVsOne;

public class ActionFactoryImpl implements ActionFactory {

    @Override
    public Action create(String actionName) throws ClassNotFoundException {
        switch (actionName) {
        case ActionNames.ACTION_ONE_VS_ONE:
            return new OneVsOne();
        case ActionNames.ACTION_BOSS_FIGHT:
            return new BossFight();
        default:
            throw new ClassNotFoundException();
        }
    }
}
