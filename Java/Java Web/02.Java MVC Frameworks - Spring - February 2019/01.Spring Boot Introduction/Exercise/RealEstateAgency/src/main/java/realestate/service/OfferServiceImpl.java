package realestate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realestate.domain.entity.Offer;
import realestate.domain.model.binding.OfferFindBindingModel;
import realestate.domain.model.service.OfferServiceModel;
import realestate.repository.OfferRepository;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(OfferServiceModel offerServiceModel) {
        if (this.validator.validate(offerServiceModel).size() != 0) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.offerRepository.saveAndFlush(this.modelMapper.map(offerServiceModel, Offer.class));
    }

    @Override
    public List<OfferServiceModel> findAll() {
        return this.offerRepository
                .findAll()
                .stream()
                .map(offer -> this.modelMapper.map(offer, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void findOffer(OfferFindBindingModel offerFindBindingModel) {
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Invalid data");
        }

        Offer offer = this.offerRepository.findAll().stream()
                .filter(o -> o.getApartmentType().equalsIgnoreCase(offerFindBindingModel.getFamilyApartmentType()))
                .filter(o -> offerFindBindingModel.getFamilyBudget()
                        .compareTo(o.getApartmentRent().multiply(BigDecimal.ONE.add(o.getAgencyCommission().divide(BigDecimal.valueOf(100), RoundingMode.CEILING)))) >= 0)
                .findFirst()
                .orElse(null);

        if (offer == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.offerRepository.delete(offer);
    }
}
