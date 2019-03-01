package realestate.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import realestate.domain.model.binding.OfferFindBindingModel;
import realestate.domain.model.binding.OfferRegisterBindingModel;
import realestate.domain.model.service.OfferServiceModel;
import realestate.service.OfferService;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel model) {
        try {
            this.offerService.register(this.modelMapper.map(model, OfferServiceModel.class));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/";
    }

    @GetMapping("/find")
    public String find() {
        return "find.html";
    }

    @PostMapping("/find")
    public String findConfirm(@ModelAttribute(name = "model") OfferFindBindingModel model) {
        try {
            this.offerService.findOffer(model);
        } catch (IllegalArgumentException iae) {
            return "redirect:/find";
        }
        return "redirect:/";
    }
}
