package p02GettersAndSetters;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> reflection = Reflection.class;

        Method[] methods = reflection.getDeclaredMethods();
        List<Method> getMethods = new ArrayList<>();
        List<Method> setMethods = new ArrayList<>();
        for (Method method : methods) {
            if(method.getName().startsWith("get")){
                getMethods.add(method);
            }
            if (method.getName().startsWith("set")){
                setMethods.add(method);
            }
        }

        getMethods.sort(Comparator.comparing(Method::getName));
        setMethods.sort(Comparator.comparing(Method::getName));

        for (Method getMethod : getMethods) {
            String returnType = getMethod.getReturnType().toString();
            System.out.printf("%s will return %s%n", getMethod.getName(), returnType);
        }
        for (Method setMethod : setMethods) {
            String parameterType = setMethod.getParameterTypes()[0].toString();
            System.out.printf("%s and will set field of %s%n", setMethod.getName(), parameterType);
        }
    }
}
