package residentevil.controllers;

import residentevil.domain.models.binding.SpreadVirusBindingModel;
import residentevil.domain.models.service.VirusServiceModel;
import residentevil.domain.models.view.CapitalsListViewModel;
import residentevil.service.api.CapitalsService;
import residentevil.service.api.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VirusController extends BaseController {

    private final CapitalsService capitalsService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalsService capitalsService,
                           VirusService virusService,
                           ModelMapper modelMapper) {
        this.capitalsService = capitalsService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/viruses/add")
    public ModelAndView renderAddVirusPage(@ModelAttribute("bindingModel") SpreadVirusBindingModel bindingModel,
                                           ModelAndView modelAndView) {
        List<CapitalsListViewModel> capitalsListViewModels = findAllCapitalsOrderByName();

        modelAndView.addObject("bindingModel", new SpreadVirusBindingModel());
        modelAndView.addObject("capitalsModels", capitalsListViewModels);
        return this.view("add-virus", modelAndView);
    }

    @PostMapping("/viruses/add")
    public ModelAndView spreadVirus(ModelAndView modelAndView,
                                    @Valid @ModelAttribute(name = "bindingModel") SpreadVirusBindingModel bindingModel,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitalsModels", findAllCapitalsOrderByName());
            return this.view("add-virus", modelAndView);
        }

        VirusServiceModel serviceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        VirusServiceModel spreadVirus = this.virusService.spreadVirus(serviceModel);

        if (spreadVirus == null) {
            //Error message: something went wrong!
            return this.view("add-virus", modelAndView);
        }

        return this.redirect("");
    }

    @GetMapping("/show")
    public ModelAndView renderShowAllPage() {

        return this.view("show");
    }

    @PostMapping("/viruses/delete/{id}")
    public ModelAndView deleteVirus(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isRemoved = this.virusService.deleteVirusById(id);

        if (!isRemoved) {
            //Error message: something went wrong!
            return this.view("show", modelAndView);
        }

        return this.redirect("show");
    }

    @GetMapping("/viruses/edit/{id}")
    public ModelAndView renderEditVirusPage(@PathVariable("id") String id,
                                            @ModelAttribute("bindingModel") SpreadVirusBindingModel bindingModel,
                                           ModelAndView modelAndView) {
        List<CapitalsListViewModel> capitalsListViewModels = findAllCapitalsOrderByName();
        modelAndView.addObject("capitalsModels", capitalsListViewModels);

        try {
            VirusServiceModel virusServiceModel = this.virusService.findById(id);
            bindingModel = this.modelMapper.map(virusServiceModel, SpreadVirusBindingModel.class);
        } catch (Exception e) {
            //Send error message: 404 Not Found
            return this.redirect("show");
        }
        modelAndView.addObject("bindingModel", bindingModel);

        return this.view("edit-virus", modelAndView);
    }

    @PostMapping("/viruses/edit/{id}")
    public ModelAndView editVirus(@PathVariable("id") String id,
                                  @Valid @ModelAttribute("bindingModel") SpreadVirusBindingModel bindingModel,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitalsModels", findAllCapitalsOrderByName());
            return this.view("edit-virus", modelAndView);
        }

        bindingModel.setId(id);
        VirusServiceModel serviceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        try {
            this.virusService.editVirus(serviceModel);
        } catch (Exception e) {
            //Something went wrong error message
            //Redirect
        }

        return this.redirect("show");
    }

    private List<CapitalsListViewModel> findAllCapitalsOrderByName() {
        return this.capitalsService.findAllSortedByName()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, CapitalsListViewModel.class))
                .collect(Collectors.toList());
    }
}


