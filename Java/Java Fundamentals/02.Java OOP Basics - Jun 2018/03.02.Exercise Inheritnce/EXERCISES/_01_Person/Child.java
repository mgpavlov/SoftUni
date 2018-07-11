package _03_Inheritance.EXERCISES._01_Person;

public class Child extends Person {
    private String name;
    private Integer age;

    public Child(String name, Integer age) {
        super(name, age);
    }


    @Override
    public void setAge(int age) {
        if (age > 15) {
            throw new IllegalArgumentException("Child's age must be lesser than 15!");
        }
        super.setAge(age);
    }
}
