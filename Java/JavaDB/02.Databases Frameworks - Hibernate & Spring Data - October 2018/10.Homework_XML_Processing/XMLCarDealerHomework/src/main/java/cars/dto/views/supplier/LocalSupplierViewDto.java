package cars.dto.views.supplier;

import cars.dto.utilities.PartIdDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierViewDto {

    @Expose
    @SerializedName("Id")
    @XmlAttribute
    private long id;

    @Expose
    @SerializedName("Name")
    @XmlAttribute
    private String name;

    @XmlTransient
    private Set<PartIdDto> parts;

    @Expose
    @SerializedName("partsCount")
    @XmlAttribute(name = "parts-count")
    private int count;

    public LocalSupplierViewDto() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PartIdDto> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartIdDto> parts) {
        this.parts = parts;
        this.setCount(parts.size());
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
