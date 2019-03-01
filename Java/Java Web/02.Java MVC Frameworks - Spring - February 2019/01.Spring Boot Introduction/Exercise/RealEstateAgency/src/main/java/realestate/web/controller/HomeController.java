package realestate.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import realestate.domain.model.view.OfferViewModel;
import realestate.service.OfferService;
import realestate.util.HtmlReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return this.parseHtml();
    }

    private String parseHtml() throws IOException {
        List<OfferViewModel> offers = this.offerService.findAll()
                .stream()
                .map(offerServiceModel -> this.modelMapper.map(offerServiceModel, OfferViewModel.class))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        if (offers.size() == 0) {
            sb.append("<div class=\"apartment\" style=\"border: red 1px solid \">")
                    .append("There aren't any offers!")
                    .append("</div>");
        } else {
            for (OfferViewModel offer : offers) {
                sb.append("<div class=\"apartment\">")
                        .append("<p>Rent: ")
                        .append(offer.getApartmentRent())
                        .append("</p>")
                        .append("<p>Type: ")
                        .append(offer.getApartmentType())
                        .append("</p>")
                        .append("<p>Commission: ")
                        .append(offer.getAgencyCommission())
                        .append("</p>")
                        .append("</div>")
                        .append(System.lineSeparator());
            }
        }
        return this.htmlReader.readHtmlFile("D:\\SoftUni\\Java\\Java Web\\02.Java MVC Frameworks - Spring - February 2019\\01.Spring Boot Introduction\\Exercise\\RealEstateAgency\\src\\main\\resources\\static\\index.html")
                .replace("{{offers}}", sb.toString().trim());
    }
}
