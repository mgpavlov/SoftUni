package p03OpinionPoll;

public class Person {
    private String name;
    private int age;

    public Person(){
        this.age = 1;
        this.name = "No name";
    }
    public Person(int age){
        this.age = age;
        this.name = "No name";
    }
    public Person(String name, int age){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }
    public String getName(){
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
}
