package cars.dto.utilities;

import java.util.Set;

public class SaleCarPartsUtilityDto {

    private Set<PartPriceUtilityDto> carParts;

    public SaleCarPartsUtilityDto() {
    }

    public Set<PartPriceUtilityDto> getCarParts() {
        return this.carParts;
    }

    public void setCarParts(Set<PartPriceUtilityDto> carParts) {
        this.carParts = carParts;
    }
}
