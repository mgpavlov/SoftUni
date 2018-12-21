package p06StrategyPattern;

public class Person/* implements Comparable<Person>*/{
    private String name;
    private int age;


    public Person(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    /*@Override
    public int compareTo(Person person) {
        return this.compareTo(person);
    }*/
    @Override
    public String toString() {
        return String.format("%s %d", this.getName(), this.getAge());
    }
}
