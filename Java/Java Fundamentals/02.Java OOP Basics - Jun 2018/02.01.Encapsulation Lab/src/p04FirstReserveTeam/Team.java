package p04FirstReserveTeam;

import p04FirstReserveTeam.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class Team {
    private String name;
    private List<Person> firstTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        this.name = name;
        this.firstTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
    }

    public void addPlayer(Person person){
        if (person.getAge() < 40 ){
            this.firstTeam.add(person);
        }else {
            this.reserveTeam.add(person);
        }
    }

    public List<Person> getFirstTeam() {
        return Collections.unmodifiableList(this.firstTeam);
    }
    public List<Person> getReserveTeam(){
        return Collections.unmodifiableList(this.reserveTeam);
    }

    @Override
    public String toString() {
        return "a";
    }
}
