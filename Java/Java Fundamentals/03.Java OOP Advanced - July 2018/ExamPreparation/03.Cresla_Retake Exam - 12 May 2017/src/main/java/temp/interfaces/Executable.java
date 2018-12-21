package temp.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Executable {

    void execute() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException;
}
