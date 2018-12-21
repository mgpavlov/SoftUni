package temp.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface ReactorFactory {
    Reactor create(String reactorType, int id, Container moduleContainer, int additionalParam) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
