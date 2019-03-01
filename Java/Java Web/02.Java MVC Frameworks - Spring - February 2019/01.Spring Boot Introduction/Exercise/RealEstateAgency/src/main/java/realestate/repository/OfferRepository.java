package realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import realestate.domain.entity.Offer;

import java.math.BigDecimal;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    void deleteByApartmentTypeEqualsAndApartmentRentLessThan(String apartmentType, BigDecimal apartmentRent);
}
