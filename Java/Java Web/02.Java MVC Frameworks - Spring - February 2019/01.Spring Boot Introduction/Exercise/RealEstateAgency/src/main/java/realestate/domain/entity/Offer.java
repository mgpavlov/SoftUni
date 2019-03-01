package realestate.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public Offer() {
    }

    public Offer(BigDecimal apartmentRent, String apartmentType, BigDecimal agencyCommission) {
        this.apartmentRent = apartmentRent;
        this.apartmentType = apartmentType;
        this.agencyCommission = agencyCommission;
    }

    @Column(name = "apartment_rent", precision = 10, scale = 2, nullable = false)
    @DecimalMin("0.01")
    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    @Column(name = "apartment_type", nullable = false)
    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @Column(name = "agency_commission", precision = 10, scale = 2, nullable = false)
    @DecimalMin("0")
    @DecimalMax("100")
    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
