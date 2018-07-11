package p03OpinionPoll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(reader.readLine());
        Set<Person> persons = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            Person person = new Person(input[0], Integer.parseInt(input[1]));
            persons.add(person);
        }

        persons.stream().filter(p->p.getAge()>30).sorted((a,b)->a.getName().compareTo(b.getName())).forEach(p->{
            System.out.printf("%s - %d%n", p.getName(), p.getAge());
        });
    }

}
