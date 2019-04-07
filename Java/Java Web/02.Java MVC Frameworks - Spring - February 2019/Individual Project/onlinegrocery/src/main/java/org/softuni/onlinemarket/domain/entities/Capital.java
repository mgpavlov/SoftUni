package org.softuni.onlinemarket.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "capitals")
public class Capital extends BaseEntity{

    private String name;
    private Double latitude;
    private Double longitude;
    private List<Virus> viruses;

    public Capital() {
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }
    @Column(nullable = false)
    public Double getLatitude() {
        return this.latitude;
    }
    @Column(nullable = false)
    public Double getLongitude() {
        return this.longitude;
    }

    @ManyToMany(mappedBy = "capitals")
    public List<Virus> getViruses() {
        return this.viruses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setViruses(List<Virus> viruses) {
        this.viruses = viruses;
    }
}
