package mostwanted.domain.dtos.races;

import mostwanted.domain.entities.RaceEntry;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDto {

    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    private String district;

    @XmlElement(name = "entries")
    private EntryImportRootDto entryImportRootDto;

    public RaceImportDto() {
    }

    @NotNull
    public Integer getLaps() {
        return this.laps;
    }

    @NotNull
    public String getDistrict() {
        return this.district;
    }

    public EntryImportRootDto getEntryImportRootDto() {
        return this.entryImportRootDto;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setEntryImportRootDto(EntryImportRootDto entryImportRootDto) {
        this.entryImportRootDto = entryImportRootDto;
    }
}
