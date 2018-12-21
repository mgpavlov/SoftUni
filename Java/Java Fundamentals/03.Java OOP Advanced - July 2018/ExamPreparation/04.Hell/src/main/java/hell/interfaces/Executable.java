package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Executable {

    void execute() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
