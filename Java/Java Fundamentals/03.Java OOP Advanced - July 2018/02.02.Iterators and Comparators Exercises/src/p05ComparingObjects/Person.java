package p05ComparingObjects;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    @Override
    public int compareTo(Person p2) {
        if (this.name.compareTo(p2.name) == 0){
            if (Integer.compare(this.age, p2.age) == 0){
                return this.town.compareTo(p2.town);
            }
            return Integer.compare(this.age, p2.age);
        }
        return this.name.compareTo(p2.name);
    }
}
