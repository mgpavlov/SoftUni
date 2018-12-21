package ExamArrPeeGee.app.contracts;

public interface ActionFactory {

    Action create(String actionName) throws ClassNotFoundException;
}
