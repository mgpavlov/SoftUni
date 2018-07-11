package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class _11_Predicate_Party {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = new ArrayList<>(Arrays.asList(reader.readLine().split("\\s+")));

        while (true) {
            String command = reader.readLine();
            if (command.equalsIgnoreCase("Party!")) {
                break;
            }

            String[] commandParams = command.split("\\s+");

            Predicate<String> conditionalAction = createConditionalAction(commandParams[1], commandParams[2]);
            executeCommand(names, commandParams[0], conditionalAction);
        }

        if (names.size() > 0) {
            System.out.println(String.join(", ", names) + " are going to the party!");
        } else {
            System.out.println("Nobody is going to the party!");
        }
    }

    private static void executeCommand(List<String> names, String command, Predicate<String> conditionalAction) {
        switch (command) {
            case "Double":
                for (int i = 0; i < names.size() ; i++) {
                    if (conditionalAction.test(names.get(i))) {
                        names.add(i, names.get(i));
                        i++;
                    }
                }
                break;
            case "Remove":
                names.removeIf(conditionalAction);
                break;
        }
    }

    private static Predicate<String> createConditionalAction(String command, String value) {
        switch (command) {
            case "StartsWith":
                return string -> string.startsWith(value);
            case "EndsWith":
                return string -> string.endsWith(value);
            case "Length":
                return string -> string.length() == Integer.parseInt(value);
            default:
                return string -> string.length() > 0;
        }
    }
}
