package hell.interfaces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Engine {
    void run() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
