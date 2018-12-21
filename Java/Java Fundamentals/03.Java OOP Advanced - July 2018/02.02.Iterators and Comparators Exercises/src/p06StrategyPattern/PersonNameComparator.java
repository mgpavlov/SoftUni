package p06StrategyPattern;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        if (Integer.compare(p1.getName().length(), p2.getName().length())== 0){
            return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
        }
        return Integer.compare(p1.getName().length(), p2.getName().length());
    }
}
