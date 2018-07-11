package p04FirstReserveTeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Team team = new Team("Minior");

        List<Person> people = new ArrayList<>();

        while (n-- > 0) {
            String[] input = reader.readLine().split(" ");
            String firstName = input[0];
            String lastName = input[1];
            int age = Integer.parseInt(input[2]);
            double salary = Double.parseDouble(input[3]);

            Person person = null;
            try {
                person = new Person(firstName, lastName, age, salary);
            } catch (IllegalArgumentException exeption) {
                System.out.println(exeption.getMessage());
            }

            if (person != null) {
                //people.add(person);
                team.addPlayer(person);
            }
        }
        /*for (Person person : people) {
            team.addPlayer(person);
        }*/

        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");
    }
}
