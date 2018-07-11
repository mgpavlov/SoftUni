package p06BirthdayCelebrations;

public abstract class Creature implements Identificatable, Birthdayable{
    private String name;
    private int age;
    private String id;
    private String birthdate;


    Creature(String name, int age, String id, String birthdate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }

    Creature(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}
