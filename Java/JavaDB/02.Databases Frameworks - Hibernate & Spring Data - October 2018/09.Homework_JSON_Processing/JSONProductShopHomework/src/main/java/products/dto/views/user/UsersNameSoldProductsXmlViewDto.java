package products.dto.views.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersNameSoldProductsXmlViewDto {

    @XmlElement(name = "user")
    private List<UserNameSoldProductsViewDto> userNameSoldProductsViewDtos;

    public UsersNameSoldProductsXmlViewDto() {
    }

    public List<UserNameSoldProductsViewDto> getUserNameSoldProductsViewDtos() {
        return this.userNameSoldProductsViewDtos;
    }

    public void setUserNameSoldProductsViewDtos(List<UserNameSoldProductsViewDto> userNameSoldProductsViewDtos) {
        this.userNameSoldProductsViewDtos = userNameSoldProductsViewDtos;
    }
}
