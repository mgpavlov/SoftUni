package cars.dto.views.car;

import cars.dto.views.part.PartViewDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsXmlViewDto {

    @Expose
    @SerializedName("Make")
    @XmlAttribute
    private String make;

    @Expose
    @SerializedName("Model")
    @XmlAttribute
    private String model;

    @Expose
    @SerializedName("TravelledDistance")
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    @Expose
    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private Set<PartViewDto> parts;

    public CarWithPartsXmlViewDto() {
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

    public Set<PartViewDto> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartViewDto> parts) {
        this.parts = parts;
    }
}
