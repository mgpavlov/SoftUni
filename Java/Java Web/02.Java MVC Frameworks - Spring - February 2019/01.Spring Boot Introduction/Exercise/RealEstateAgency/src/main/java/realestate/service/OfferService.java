package realestate.service;

import realestate.domain.model.binding.OfferFindBindingModel;
import realestate.domain.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    void register(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> findAll();

    void findOffer(OfferFindBindingModel offerFindBindingModel);
}
