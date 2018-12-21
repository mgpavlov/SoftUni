package cars.dto.bindings;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsImportXmlDto {

    @Expose
    @XmlElement(name = "part")
    private List<PartImportDto> partImportDtos;

    public PartsImportXmlDto() {
    }

    public List<PartImportDto> getPartImportDtos() {
        return this.partImportDtos;
    }

    public void setPartImportDtos(List<PartImportDto> partImportDtos) {
        this.partImportDtos = partImportDtos;
    }
}
