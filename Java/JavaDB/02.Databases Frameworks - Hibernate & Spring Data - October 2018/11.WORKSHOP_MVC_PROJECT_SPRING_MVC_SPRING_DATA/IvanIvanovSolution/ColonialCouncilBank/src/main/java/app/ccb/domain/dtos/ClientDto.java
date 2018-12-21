package app.ccb.domain.dtos;

import javax.validation.constraints.NotEmpty;

public class ClientDto {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Integer age;
    private String appointedEmployee;

    public ClientDto() {
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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAppointedEmployee() {
        return this.appointedEmployee;
    }

    public void setAppointedEmployee(String appointedEmployee) {
        this.appointedEmployee = appointedEmployee;
    }
}
