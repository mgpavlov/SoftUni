package p11CatLady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<String, Cat> catByName = new HashMap<>();

        String command = in.nextLine();
        while (!"End".equals(command)) {
            String[] tokens = command.split("\\s+");

            String type = tokens[0];
            String name = tokens[1];
            Double characteristic = Double.parseDouble(tokens[2]);

            Cat cat = new Cat(name, characteristic, type);
            catByName.put(name, cat);

            command = in.nextLine();
        }

        String searchedCat = in.nextLine();
        System.out.println(catByName.get(searchedCat));
    }
}
