package org.softuni.resident_evil.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "capitals")
public class Capital {
    private Long id;

    private String name;

    private Double longitude;

    private Double latitude;

    private Set<Virus> viruses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false, insertable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @ManyToMany(mappedBy = "capitals")
    public Set<Virus> getViruses() {
        return viruses;
    }

    public void setViruses(Set<Virus> viruses) {
        this.viruses = viruses;
    }
}
