package cars.dto.bindings;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDto {

    @Expose
    @XmlAttribute
    private String name;

    @Expose
    @XmlElement(name = "birth-date")
    private Date birthDate;

    @Expose
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
