package p05BordeControl;


public abstract class Person implements Identificatable {
    private String name;
    private int age;
    private String id;


    Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
