package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "race_entries")
public class RaceEntry extends BaseEntity  {
    private boolean hasFinished;
    private Double finishTime;
    private Car car;
    private Racer racer;
    private Race race;

    public RaceEntry() {
    }

    @Column(name = "has_finished")
    public boolean isHasFinished() {
        return this.hasFinished;
    }

    @Column(name = "finish_time")
    public Double getFinishTime() {
        return this.finishTime;
    }

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(
            name = "car_id",
            referencedColumnName = "id"
    )
    public Car getCar() {
        return this.car;
    }

    @ManyToOne(targetEntity = Racer.class)
    @JoinColumn(
            name = "racer_id",
            referencedColumnName = "id"
    )
    public Racer getRacer() {
        return this.racer;
    }

    @ManyToOne(targetEntity = Race.class)
    @JoinColumn(
            name = "race_id",
            referencedColumnName = "id"
    )
    public Race getRace() {
        return this.race;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
