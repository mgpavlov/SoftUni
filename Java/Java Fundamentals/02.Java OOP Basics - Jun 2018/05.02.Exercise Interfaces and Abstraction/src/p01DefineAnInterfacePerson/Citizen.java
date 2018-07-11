package p01DefineAnInterfacePerson;

public class Citizen implements Person{
    private String name;
    private int age;

    public Citizen(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
