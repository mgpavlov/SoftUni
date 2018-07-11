package p06BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Creature> creatures = new ArrayList<>();

        while (true) {
            String[] input = reader.readLine().split("\\s+");
            if ("End".equals(input[0])) {
                break;
            }
            if (input[0].equals("Citizen")){
                Creature citizen = new Citizen(input[1], Integer.parseInt(input[2]), input[3], input[4]);
                creatures.add(citizen);
            }else if (input[0].equals("Pet")){
                Creature pet = new Pet(input[1], input[2]);
                creatures.add(pet);
            }
        }

        String year = reader.readLine();

        creatures.stream().filter(p->p.getBirthdate().endsWith(year)).forEach(e->{
            System.out.println(e.getBirthdate());
        });
    }
}
