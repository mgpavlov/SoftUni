package org.softuni.residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.models.binding.VirusAddBindingModel;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.domain.models.view.AllVirusesViewModel;
import org.softuni.residentevil.domain.models.view.CapitalViewModel;
import org.softuni.residentevil.service.CapitalService;
import org.softuni.residentevil.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute(name = "virusBindingModel") VirusAddBindingModel virusBindingModel, ModelAndView modelAndView) {
        return loadAndReturnModelAndView(virusBindingModel, modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "virusBindingModel") VirusAddBindingModel virusBindingModel, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors() ||
                this.virusService.registerVirus(this.modelMapper.map(virusBindingModel, VirusServiceModel.class)) == null) {

            return loadAndReturnModelAndView(virusBindingModel, modelAndView);
        }
        return super.redirect("/viruses/show");
    }

    private ModelAndView loadAndReturnModelAndView(VirusAddBindingModel virusBindingModel, ModelAndView modelAndView) {
        modelAndView.addObject("virusBindingModel", virusBindingModel);

        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream().map(c -> modelMapper.map(c, CapitalViewModel.class))
                .collect(Collectors.toList()));

        return super.view("add-virus", modelAndView);
    }

    @GetMapping("/show")
    public ModelAndView allViruses(ModelAndView modelAndView) {
        List<AllVirusesViewModel> viruses = this.virusService.findAllViruses()
                .stream()
                .map(v -> modelMapper.map(v, AllVirusesViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("allViruses", viruses);

        modelAndView.addObject(
                "allCapitals",
                this.capitalService.findAllCapitals()
                                .stream()
                                .map(c -> modelMapper.map(c, CapitalViewModel.class))
                                .collect(Collectors.toList())
        );

        return super.view("show", modelAndView);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<AllVirusesViewModel> allViruses() {
        return this.virusService.findAllViruses()
                .stream()
                .map(v -> modelMapper.map(v, AllVirusesViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/capitals")
    @ResponseBody
    public List<CapitalViewModel> allCapitals() {
        return this.capitalService.findAllCapitals()
                .stream()
                .map(c -> modelMapper.map(c, CapitalViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/edit")
    public ModelAndView editViruses(@RequestParam("id") String id,
                                    ModelAndView modelAndView,
                                    @ModelAttribute("virusBindingModel") VirusAddBindingModel virusBindingModel) {
        virusBindingModel = this.modelMapper.map(this.virusService.findVirusById(id), VirusAddBindingModel.class);
        modelAndView.addObject("virusBindingModel", virusBindingModel);

        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream().map(c -> modelMapper.map(c, CapitalViewModel.class))
                .collect(Collectors.toList()));
        return this.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit")
    public ModelAndView editVirusConfirm(ModelAndView modelAndView,
                                         @RequestParam("id") String id,
                                         @Valid @ModelAttribute("virusBindingModel") VirusAddBindingModel virusBindingModel,
                                         BindingResult bindingResult) {
        VirusServiceModel virusServiceModel = modelMapper.map(virusBindingModel, VirusServiceModel.class);
        virusServiceModel.setId(id);
        if (bindingResult.hasErrors() || !this.virusService.update(virusServiceModel)) {
            modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                    .stream().map(c -> modelMapper.map(c, CapitalViewModel.class))
                    .collect(Collectors.toList()));
            modelAndView.addObject("virusBindingModel", virusBindingModel);
            return this.view("edit-virus", modelAndView);
        }
        return this.redirect("/viruses/show");
    }

    @GetMapping("/delete")
    public ModelAndView deleteVirus(@RequestParam("id") String id,
                                    ModelAndView modelAndView,
                                    @ModelAttribute("virusBindingModel") VirusAddBindingModel virusBindingModel) {
        virusBindingModel = this.modelMapper.map(this.virusService.findVirusById(id), VirusAddBindingModel.class);
        modelAndView.addObject("virusBindingModel", virusBindingModel);

        modelAndView.addObject("capitals", this.virusService.findVirusById(id).getCapitals()
                .stream().map(c -> modelMapper.map(c, CapitalViewModel.class))
                .collect(Collectors.toList()));

        return this.view("delete-virus", modelAndView);
    }


    @PostMapping("/delete")
    public ModelAndView deleteVirusConfirm(@RequestParam("id") String id) {
        this.virusService.deleteVirus(id);
        return this.redirect("/viruses/show");
    }

}
