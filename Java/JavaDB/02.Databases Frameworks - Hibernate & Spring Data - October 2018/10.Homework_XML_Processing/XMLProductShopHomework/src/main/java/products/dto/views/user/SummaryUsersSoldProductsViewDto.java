package products.dto.views.user;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SummaryUsersSoldProductsViewDto {

    @Expose
    @XmlAttribute(name = "count")
    private int usersCount;

    @Expose
    @XmlElement(name = "user")
    private Set<UserSoldProductsViewDto> users;

    public SummaryUsersSoldProductsViewDto() {
    }

    public int getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserSoldProductsViewDto> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserSoldProductsViewDto> users) {
        this.users = users;
        this.setUsersCount(users.size());
    }
}
