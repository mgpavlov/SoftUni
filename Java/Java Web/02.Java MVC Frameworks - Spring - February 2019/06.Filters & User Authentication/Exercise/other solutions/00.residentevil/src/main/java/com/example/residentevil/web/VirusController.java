package com.example.residentevil.web;

import com.example.residentevil.domain.models.binding.SpreadVirusBindingModel;
import com.example.residentevil.domain.models.service.VirusServiceModel;
import com.example.residentevil.domain.models.view.CapitalsListViewModel;
import com.example.residentevil.domain.models.view.VirusListViewModel;
import com.example.residentevil.service.api.CapitalsService;
import com.example.residentevil.service.api.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView renderAddVirusPage(@ModelAttribute("bindingModel") SpreadVirusBindingModel bindingModel,
                                           ModelAndView modelAndView) {
        List<CapitalsListViewModel> capitalsListViewModels = findAllCapitalsOrderByName();

        modelAndView.addObject("bindingModel", new SpreadVirusBindingModel());
        modelAndView.addObject("capitalsModels", capitalsListViewModels);
        return this.view("add-virus", modelAndView);
    }

    @PostMapping("/viruses/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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

        return this.redirect("home");
    }

    @GetMapping("/show-viruses")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView renderShowAllPage(ModelAndView modelAndView) {

        List<VirusListViewModel> viewModels = this.virusService.findAllViruses()
                .stream()
                .map(vServiceModel -> this.modelMapper.map(vServiceModel, VirusListViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("viewModels", viewModels);
        return this.view("show-viruses", modelAndView);
    }

    @GetMapping("viruses/all")
    @ResponseBody
    public List<VirusListViewModel> allViruses() {

        List<VirusListViewModel> viewModels = this.virusService.findAllViruses()
                .stream()
                .map(vServiceModel -> this.modelMapper.map(vServiceModel, VirusListViewModel.class))
                .collect(Collectors.toList());

        return viewModels;
    }

    @GetMapping("capitals/all")
    @ResponseBody
    public List<CapitalsListViewModel> allCapitals() {
        return this.findAllCapitalsOrderByName();
    }

    @PostMapping("/viruses/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteVirus(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isRemoved = this.virusService.deleteVirusById(id);

        if (!isRemoved) {
            //Error message: something went wrong!
            return this.view("show-viruses", modelAndView);
        }

        return this.redirect("show-viruses");
    }

    @GetMapping("/viruses/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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
            return this.redirect("show-viruses");
        }
        modelAndView.addObject("bindingModel", bindingModel);

        return this.view("edit-virus", modelAndView);
    }

    @PostMapping("/viruses/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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

        return this.redirect("show-viruses");
    }

    private List<CapitalsListViewModel> findAllCapitalsOrderByName() {
        return this.capitalsService.findAllSortedByName()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, CapitalsListViewModel.class))
                .collect(Collectors.toList());
    }
}
