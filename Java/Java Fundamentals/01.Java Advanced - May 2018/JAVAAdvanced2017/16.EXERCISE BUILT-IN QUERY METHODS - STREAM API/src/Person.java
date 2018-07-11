public class Person {
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
