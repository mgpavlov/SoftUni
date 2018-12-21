package cars.dto.bindings;

import cars.dto.utilities.PartIdDto;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {

    @Expose
    @XmlElement
    private String make;

    @Expose
    @XmlElement
    private String model;

    @Expose
    @XmlElement(name = "travelled-distance")
    private long travelledDistance;

    private Set<PartIdDto> parts;

    public CarImportDto() {
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartIdDto> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartIdDto> parts) {
        this.parts = parts;
    }
}
