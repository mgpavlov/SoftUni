package com.company;

import java.util.*;

public class _05_FilterByAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer n = Integer.parseInt(scanner.nextLine());

        LinkedHashMap<String, Integer> sorted_map = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String[] numbers = scanner.nextLine().split(", ");
            String name = numbers[0];
            Integer years = Integer.parseInt(numbers[1]);

            sorted_map.put(name, years);

        }
        String condition = scanner.nextLine();
        Integer age = Integer.parseInt(scanner.nextLine());
        String[] format = scanner.nextLine().split(" ");

        LinkedHashMap<String, Integer> filteredNames = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> stringIntegerEntry : sorted_map.entrySet()) {
            String name = stringIntegerEntry.getKey();
            int currentAge = stringIntegerEntry.getValue();

            if (isInCondition(currentAge, condition, age))
                filteredNames.put(name, currentAge);
        }
        boolean onlyName = false;
        boolean onlyAge = false;
        for (String str : format) {
            if (str.toLowerCase().equals("name"))
                onlyName = true;
            else
                onlyAge = true;
        }

        boolean bothNameAndAge = onlyName && onlyAge;

        for (Map.Entry<String, Integer> stringIntegerEntry : filteredNames.entrySet()) {
            if (bothNameAndAge)
                System.out.println(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue());
            else if (onlyName)
                System.out.println(stringIntegerEntry.getKey());
            else
                System.out.println(stringIntegerEntry.getValue());
        }

    }

    private static boolean isInCondition(int currentAge, String condition, int conditionAge) {
        if (condition.equals("younger")) {
            return currentAge <= conditionAge;

        } else {
            if (currentAge >= conditionAge) {
                return true;
            } else {
                return false;
            }
        }
    }


}
