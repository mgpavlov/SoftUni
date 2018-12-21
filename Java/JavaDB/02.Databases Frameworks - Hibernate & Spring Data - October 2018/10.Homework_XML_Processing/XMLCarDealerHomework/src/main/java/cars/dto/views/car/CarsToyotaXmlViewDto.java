package cars.dto.views.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToyotaXmlViewDto {

    @XmlElement(name = "car")
    private List<CarToyotaViewDto> carToyotaViewDtos;

    public CarsToyotaXmlViewDto() {
    }

    public List<CarToyotaViewDto> getCarToyotaViewDtos() {
        return this.carToyotaViewDtos;
    }

    public void setCarToyotaViewDtos(List<CarToyotaViewDto> carToyotaViewDtos) {
        this.carToyotaViewDtos = carToyotaViewDtos;
    }
}
