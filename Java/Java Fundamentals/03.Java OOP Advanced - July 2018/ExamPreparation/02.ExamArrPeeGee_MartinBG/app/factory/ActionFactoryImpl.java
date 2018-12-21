package ExamArrPeeGee.app.factory;

import ExamArrPeeGee.app.constants.ActionNames;
import ExamArrPeeGee.app.contracts.Action;
import ExamArrPeeGee.app.contracts.ActionFactory;
import ExamArrPeeGee.app.models.actions.BossFight;
import ExamArrPeeGee.app.models.actions.OneVsOne;

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
