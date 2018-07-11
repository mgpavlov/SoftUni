package p05BordeControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> Person = new ArrayList<>();

        while (true) {
            String[] input = reader.readLine().split("\\s+");
            if ("End".equals(input[0])) {
                break;
            }
            if (input.length == 3){
                Person citizen = new Citizen(input[0], Integer.parseInt(input[1]), input[2]);
                Person.add(citizen);
            }else {
                Person robot = new Robot(input[0], input[1]);
                Person.add(robot);
            }
        }

        String fakeNum = reader.readLine();

        Person.stream().filter(p->p.getId().endsWith(fakeNum)).forEach(e->{
            System.out.println(e.getId());
        });
    }
}
