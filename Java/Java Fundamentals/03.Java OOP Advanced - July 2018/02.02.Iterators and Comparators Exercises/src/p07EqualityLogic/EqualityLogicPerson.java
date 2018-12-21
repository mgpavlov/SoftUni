package p07EqualityLogic;

import java.util.Comparator;

public class EqualityLogicPerson implements Comparable<EqualityLogicPerson>, Comparator<EqualityLogicPerson> {

    private String name;
    private int age;

    public EqualityLogicPerson(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object obj) {

        EqualityLogicPerson p = (EqualityLogicPerson) obj;
        return this.name.equals(p.name) && this.age == p.age;
    }

    @Override
    public int hashCode() {
        return this.name.length() * this.age * 179;
    }

    @Override
    public int compare(EqualityLogicPerson o1, EqualityLogicPerson o2) {
        return o1.name.compareTo(o2.name) != 0 ? o1.name.compareTo(o2.name)
                : Integer.compare(o1.age, o2.age);
    }

    @Override
    public int compareTo(EqualityLogicPerson o) {
        return this.compare(this, o);
    }
}

