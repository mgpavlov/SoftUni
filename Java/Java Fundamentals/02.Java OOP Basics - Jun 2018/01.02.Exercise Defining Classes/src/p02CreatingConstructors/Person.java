package p02CreatingConstructors;

public class Person {
    private static final String DEFAULT_NAME  = "No name";
    private static final int DEFAULT_AGE = 1;
    private String name;
    private int age;

    public Person(){
        this(DEFAULT_NAME, DEFAULT_AGE);
        /*this.age = DEFAULT_AGE;
        this.name = DEFAULT_NAME;*/
    }
    public Person(int age){
        this(DEFAULT_NAME, age);
       /* this.age = age;
        this.name = DEFAULT_NAME;*/
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
