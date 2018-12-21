package p01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Class<RichSoilLand> rf = RichSoilLand.class;
        Field[] allFields = rf.getDeclaredFields();

        printAllData(reader, allFields);
    }

    private static void printAllData(BufferedReader reader, Field[] allFields) throws IOException {
        while (true) {
            String input = reader.readLine();
            if ("HARVEST".equals(input)) {
                break;
            }
            switch (input) {
                case "private":
                    for (Field field : allFields) {
                        if (Modifier.isPrivate(field.getModifiers())) {
                            printResult(field);
                        }
                    }
                    break;
                case "protected":
                    for (Field field : allFields) {
                        if (Modifier.isProtected(field.getModifiers())) {
                            printResult(field);
                        }
                    }
                    break;
                case "public":
                    for (Field field : allFields) {
                        if (Modifier.isPublic(field.getModifiers())) {
                            printResult(field);
                        }
                    }
                    break;
                case "all":
                    for (Field field : allFields) {
                        printResult(field);
                    }
                    break;
            }
        }
    }

    private static void printResult(Field field) {
        String type = field.getType().getSimpleName();
        String modifier = Modifier.toString(field.getModifiers());
        String name = field.getName();
        System.out.println(modifier + " " + type + " " + name);
    }
}
