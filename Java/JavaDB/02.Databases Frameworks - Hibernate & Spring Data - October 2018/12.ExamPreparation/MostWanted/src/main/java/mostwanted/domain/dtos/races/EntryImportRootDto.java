package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {
    @XmlElement(name = "entry")
    private EntryImportDto[] entryImportDto;

    public EntryImportRootDto() {
    }

    public EntryImportDto[] getEntryImportDto() {
        return this.entryImportDto;
    }

    public void setEntryImportDto(EntryImportDto[] entryImportDto) {
        this.entryImportDto = entryImportDto;
    }
}
