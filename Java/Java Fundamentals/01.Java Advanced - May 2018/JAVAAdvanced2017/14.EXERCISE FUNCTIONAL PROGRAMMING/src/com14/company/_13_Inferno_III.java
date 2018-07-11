package com14.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class _13_Inferno_III {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] gems = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Map<String, Predicate<Integer>> changes = new HashMap<>();

        while (true) {
            String command = reader.readLine();
            if ("Forge".equalsIgnoreCase(command)) {
                break;
            }

            String[] params = command.split(";");
            Predicate<Integer> change = createPredicate(params[1], params[2], gems);
            switch (params[0]) {
                case "Exclude":
                    changes.put(params[1] + params[2], change);
                    break;
                case "Reverse":
                    changes.remove(params[1] + params[2]);
                    break;
            }
        }

        for (int i = 0; i < gems.length; i++) {
            boolean toRemove = false;
            for (Map.Entry<String, Predicate<Integer>> change : changes.entrySet()) {
                if (change.getValue().test(i)) {
                    toRemove = true;
                    break;
                }
            }

            if (!toRemove) {
                System.out.print(gems[i] + " ");
            }
        }
    }

    private static Predicate<Integer> createPredicate(String condition, String value, int[] gems) {
        Integer number = Integer.parseInt(value);
        switch (condition) {
            case "Sum Left":
                return index -> (index > 0 ? gems[index - 1] : 0) + gems[index] == number;
            case "Sum Right":
                return index -> gems[index] + (index < gems.length - 1 ? gems[index + 1] : 0) == number;
            case "Sum Left Right":
                return index -> (index > 0 ? gems[index - 1] : 0) + gems[index] +
                        (index < gems.length - 1 ? gems[index + 1] : 0) == number;
        }
        return x -> Objects.equals(x, x);
    }
}
