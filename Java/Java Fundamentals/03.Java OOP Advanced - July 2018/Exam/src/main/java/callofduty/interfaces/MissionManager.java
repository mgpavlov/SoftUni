package callofduty.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MissionManager {
    String agent(List<String> arguments);

    String request(List<String> arguments) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    String complete(List<String> arguments) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException;

    String status(List<String> arguments);

    String over(List<String> arguments);
}
