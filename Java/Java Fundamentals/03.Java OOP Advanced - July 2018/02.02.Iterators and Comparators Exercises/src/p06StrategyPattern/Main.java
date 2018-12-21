package p06StrategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<Person> sortedByName = new TreeSet<>(new PersonNameComparator());
        Set<Person> sortedByAge = new TreeSet<>(new PersonAgeComparator());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());
        while (lines-- > 0){
            String[] personArgs = reader.readLine().split("\\s+");
            sortedByName.add(new Person(personArgs[0], Integer.valueOf(personArgs[1])));
            sortedByAge.add(new Person(personArgs[0], Integer.valueOf(personArgs[1])));
        }

        sortedByName.forEach(p->{
            System.out.println(p.toString());
        });
        sortedByAge.forEach(p->{
            System.out.println(p.toString());
        });
    }
}
