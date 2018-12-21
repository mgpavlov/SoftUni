package products.dto.views.user;

import com.google.gson.annotations.Expose;

public class UserNameViewDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    public UserNameViewDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
