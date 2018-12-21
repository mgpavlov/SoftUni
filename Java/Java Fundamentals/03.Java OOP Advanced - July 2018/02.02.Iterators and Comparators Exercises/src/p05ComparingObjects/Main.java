package p05ComparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Person> people = new ArrayList<>();
        String command = reader.readLine();
        while (!"END".equals(command)) {
            String[] personArgs = command.split("\\s+");
            String name = personArgs[0];
            int age = Integer.parseInt(personArgs[1]);
            String town = personArgs[2];

            Person person = new Person(name, age, town);
            people.add(person);

            command = reader.readLine();
        }

        int n = Integer.parseInt(reader.readLine()) - 1;
        Person personToCompare = people.get(n);

        int numberOfEqualPeople = 0;
        int numberOfNotEqualPeople = 0;
        int totalNumberOfPeople = people.size();
        for (Person person : people) {
            if (personToCompare.compareTo(person) == 0) {
                numberOfEqualPeople++;
            }
        }

        numberOfNotEqualPeople = totalNumberOfPeople - numberOfEqualPeople;

        if (numberOfEqualPeople == 1) {
            System.out.println("No matches");
        } else {
            System.out.println(String.format("%d %d %d", numberOfEqualPeople, numberOfNotEqualPeople, totalNumberOfPeople));
        }
    }
}
