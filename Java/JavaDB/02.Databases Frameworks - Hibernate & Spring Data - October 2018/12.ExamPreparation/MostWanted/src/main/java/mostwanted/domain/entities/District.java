package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "districts")
public class District extends BaseEntity  {
    private String name;
    private Town town;

    public District() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(
            name = "town_id",
            referencedColumnName = "id"
    )
    public Town getTown() {
        return this.town;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
