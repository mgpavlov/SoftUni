package softuni.residentevil.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "capitals")
public class Capital extends BaseEntity {
  private String name;
  private Double latitude;
  private Double longitude;

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @Column(name = "longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}
