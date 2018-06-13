import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p10GroupByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Person> personList = new ArrayList<>();

        while (true) {
            String line = bf.readLine();
            if ("END".equalsIgnoreCase(line)) {
                break;
            }

            String[] tokens = line.split("\\s+");
            String name = tokens[0] + " " + tokens[1];
            Integer group = Integer.valueOf(tokens[2]);

            Person person = new Person(name, group);
            personList.add(person);
        }

        //personList.stream().map(person ->)

        Map<Integer, List<Person>> result = personList.stream()
                .collect(Collectors.groupingBy(Person::getGroup));

        personList.stream()
                .collect(Collectors.groupingBy(Person::getGroup))
                .forEach((key, value) -> {
                    System.out.printf("%d - ", key);
                    System.out.println(value
                            .stream()
                            //.map(p -> p.getName())
                            .map(Person::getName)
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ")));
                });
    }
    public static class Person {
        private String name;
        private Integer group;

        Person(String name, Integer group) {
            this.name = name;
            this.group = group;
        }

        public Person() {
        }

        String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        Integer getGroup() {
            return this.group;
        }

        public void setGroup(Integer group) {
            this.group = group;
        }
    }
}
