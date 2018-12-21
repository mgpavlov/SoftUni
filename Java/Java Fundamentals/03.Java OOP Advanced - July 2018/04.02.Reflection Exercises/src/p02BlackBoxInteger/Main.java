package p02BlackBoxInteger;

import p02BlackBoxInteger.com.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.*;

public class Main {

    private static final String CLASS_BLACK_BOX_INT_PATH = "p02BlackBoxInteger.com.BlackBoxInt";

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Class<?> rf = Class.forName(CLASS_BLACK_BOX_INT_PATH);
		Constructor<?> constructor = rf.getDeclaredConstructor();
		constructor.setAccessible(true);
		BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance();
        Map<String, Class<?>[]> nameAndParametersType = new LinkedHashMap<>();
		Method[] methods = rf.getDeclaredMethods();
		Field field = rf.getDeclaredField("innerValue");
		field.setAccessible(true);

		for (Method method : methods) {
			method.setAccessible(true);
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            nameAndParametersType.put(name, parameterTypes);
		}

        try {
            while (true) {
                String input = reader.readLine();
                String[] tokens = input.split("_");
                String command = tokens[0];
                int value = Integer.parseInt(tokens[1]);

                if ("END".equals(input)) {
                    break;
                }
                invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
               /* switch (command){
                    case "add":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                    case "subtract":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                    case "divide":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                    case "multiply":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                    case "rightShift":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                    case "leftShift":
                        invokeMethod(rf, blackBoxInt, nameAndParametersType, field, command, value);
                        break;
                }*/
            }
        }catch (Exception ignored){

        }
	}

    private static void invokeMethod(Class<?> rf, BlackBoxInt blackBoxInt, Map<String, Class<?>[]> nameAndParametersType, Field field, String command, int value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = rf.getDeclaredMethod(command, nameAndParametersType.get(command));
        method.setAccessible(true);
        method.invoke(blackBoxInt, value);
        System.out.println(field.get(blackBoxInt));
    }
}
