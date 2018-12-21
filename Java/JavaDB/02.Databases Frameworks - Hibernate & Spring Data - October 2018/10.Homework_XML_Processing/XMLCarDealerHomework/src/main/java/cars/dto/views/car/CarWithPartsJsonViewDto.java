package cars.dto.views.car;

import cars.dto.views.part.PartViewDto;
import com.google.gson.annotations.Expose;

import java.util.Set;

public class CarWithPartsJsonViewDto {

    @Expose
    private CarViewDto car;

    @Expose
    private Set<PartViewDto> parts;

    public CarWithPartsJsonViewDto() {
    }

    public CarViewDto getCar() {
        return this.car;
    }

    public void setCar(CarViewDto car) {
        this.car = car;
    }

    public Set<PartViewDto> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartViewDto> parts) {
        this.parts = parts;
    }
}
