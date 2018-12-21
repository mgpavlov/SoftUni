package temp.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Manager {
    String reactorCommand(List<String> arguments) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    String moduleCommand(List<String> arguments);

    String reportCommand(List<String> arguments);

    String exitCommand(List<String> arguments) throws NoSuchFieldException, IllegalAccessException;
}
