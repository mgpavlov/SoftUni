package p03HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> rf = Reflection.class;
        Method[] methods = rf.getDeclaredMethods();
        Field[] fields = rf.getDeclaredFields();
        Arrays.sort(methods, Comparator.comparing(Method::getName));
        Arrays.sort(fields, Comparator.comparing(Field::getName));

        for (Field field : fields) {
            if(!Modifier.isPrivate(field.getModifiers())){
                System.out.printf("%s must be private!%n", field.getName());
            }
        }
        for (Method method : methods) {
            if (method.getName().startsWith("get")){
                if(!Modifier.isPublic(method.getModifiers())){
                    System.out.printf("%s have to be public!%n", method.getName());
                }
            }
            if (method.getName().startsWith("set")){
                if(!Modifier.isPrivate(method.getModifiers())){
                    System.out.printf("%s have to be private!%n", method.getName());
                }
            }
        }
    }
}
