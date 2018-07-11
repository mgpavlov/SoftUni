package p02MultipleImplementation;

public class Citizen implements Person, Identifiable, Birthable {
    private String name;
    private int age;
    private String birthdate;
    private String id;
    public Citizen(String name, int age, String birthdate, String id) {
        this.setName(name);
        this.setAge(age);
        this.setId(id);
        this.setBirthdate(birthdate);
    }
    public String getId() {
        return this.id;
    }

    public String getBirthdate() {
        return this.birthdate;

    }

    private void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    private void setId(String id) {
        this.id = id;
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

    @Override
    public String birthdate() {
        return this.birthdate;
    }

    @Override
    public String id() {
        return this.id;
    }
}
