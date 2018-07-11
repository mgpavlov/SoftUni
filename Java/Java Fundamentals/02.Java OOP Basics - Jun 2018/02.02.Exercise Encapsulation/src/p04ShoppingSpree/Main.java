package p04ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> persons = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        String[] nameMoney = reader.readLine().split("[=;]");

        for (int i = 0; i < nameMoney.length; i += 2) {
            String personName = nameMoney[i];
            double money = Double.parseDouble(nameMoney[i + 1]);
            Person person = null;

            try {
                person = new Person(personName, money);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            persons.put(personName, person);
        }
        String[] productMoney = reader.readLine().split("[=;]");

        for (int i = 0; i < productMoney.length; i += 2) {
            String productName = productMoney[i];
            double cost = Double.parseDouble(productMoney[i + 1]);
            Product product = null;

            try {
                product = new Product(productName, cost);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            products.put(productName, product);
        }

        while (true) {
            String input = reader.readLine();
            if (input.equals("END")) {
                break;
            }
            String[] tokens = input.split("\\s+");
            String productName = tokens[1];
            String personName = tokens[0];
            if (persons.get(personName).getMoney() >= products.get(productName).getCost()) {
                try {

                    System.out.printf("%s bought %s%n", personName, productName);
                    persons.get(personName).setProducts(productName);
                    persons.get(personName).reduceMoney(products.get(productName).getCost());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.printf("%s can't afford %s%n", personName, productName);
            }
        }

        for (Person person : persons.values()) {
            if (person == null) {
                continue;
            }
            System.out.println(person.toString());
        }
    }
}
