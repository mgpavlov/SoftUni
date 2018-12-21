package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "races")
public class Race extends BaseEntity  {
    private Integer laps;
    private District district;
    private Set<RaceEntry> entries;

    public Race() {
    }

    @Column(name = "laps", nullable = false, columnDefinition = "INT(11) default 0")
    public Integer getLaps() {
        return this.laps;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(
            name = "district_id",
            referencedColumnName = "id",
            nullable = false
    )
    public District getDistrict() {
        return this.district;
    }

    @OneToMany(targetEntity = RaceEntry.class, mappedBy = "race")
    public Set<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
