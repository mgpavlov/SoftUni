package realestate.domain.model.binding;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class
OfferFindBindingModel {
    private BigDecimal familyBudget;
    private String familyApartmentType;
    private String familyName;

    public OfferFindBindingModel() {
    }

    @NotNull
    @DecimalMin("0.01")
    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    @NotNull
    @NotEmpty
    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    @NotNull
    @NotEmpty
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
