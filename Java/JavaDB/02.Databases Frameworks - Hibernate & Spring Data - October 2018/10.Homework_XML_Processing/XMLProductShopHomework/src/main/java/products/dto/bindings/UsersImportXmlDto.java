package products.dto.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportXmlDto {

    @XmlElement(name = "user")
    private List<UserImportDto> userImportDtos;

    public UsersImportXmlDto() {
    }

    public List<UserImportDto> getUserImportDtos() {
        return this.userImportDtos;
    }

    public void setUserImportDtos(List<UserImportDto> userImportDtos) {
        this.userImportDtos = userImportDtos;
    }
}
