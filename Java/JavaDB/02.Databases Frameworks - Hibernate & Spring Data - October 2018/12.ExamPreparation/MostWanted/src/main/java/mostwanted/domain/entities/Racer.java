package mostwanted.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "racers")
public class Racer extends BaseEntity  {
    /*•	id – integer number, primary identification field.
•	name – a string (required, unique).
•	age – an integer number.
•	bounty – a decimal data type.
•	homeTown – a Town entity.
•	cars – a collection of Car entity.
*/

    private String name;
    private Integer age;
    private BigDecimal bounty;
    private Town homeTown;
    private Set<Car> cars;
    private Set<RaceEntry> raceEntries;

    public Racer() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    @Column(name = "bounty")
    public BigDecimal getBounty() {
        return this.bounty;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(
            name = "town_id",
            referencedColumnName = "id"
    )
    public Town getHomeTown() {
        return this.homeTown;
    }

    @OneToMany(targetEntity = Car.class, mappedBy = "racer")
    public Set<Car> getCars() {
        return this.cars;
    }

    @OneToMany(mappedBy = "racer", targetEntity = RaceEntry.class)
    public Set<RaceEntry> getRaceEntries() {
        return this.raceEntries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void setRaceEntries(Set<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
