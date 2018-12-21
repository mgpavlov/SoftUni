package mostwanted.domain.dtos.raceentries;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDto {

    @XmlAttribute(name = "has-finished")
    private boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private String carId;

    @XmlElement(name = "racer")
    private String racerName;

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public String getCarId() {
        return this.carId;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
