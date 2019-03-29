package org.softuni.resident_evil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.models.binding.VirusAddBindingModel;
import org.softuni.resident_evil.domain.models.binding.VirusDeleteBindingModel;
import org.softuni.resident_evil.domain.models.binding.VirusEditBindingModel;
import org.softuni.resident_evil.domain.models.service.VirusServiceModel;
import org.softuni.resident_evil.domain.models.view.VirusTableViewModel;
import org.softuni.resident_evil.service.CapitalService;
import org.softuni.resident_evil.service.VirusService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final VirusService virusService;
    private final CapitalService capitalService;
    private final ModelMapper mapper;


    public VirusController(ModelMapper mapper, VirusService virusService, CapitalService capitalService) {
        this.mapper = mapper;
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("/show")
    public ModelAndView allVirusesView() {
        List<VirusTableViewModel> allViruses = this.virusService.getAllViruses();
        return view("/all-viruses")
                .addObject("allViruses", allViruses);

    }

    @GetMapping("/add")
    public ModelAndView addView(@ModelAttribute("model") VirusAddBindingModel model) {
        return prepareVirusModel("add-form", true);
    }

    @PostMapping("/add")
    public ModelAndView addAction(@Valid @ModelAttribute("model") VirusAddBindingModel model,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return prepareVirusModel("add-form", true);
        }
        VirusServiceModel serviceModel = this.mapper.map(model, VirusServiceModel.class);
        if (this.virusService.createVirus(serviceModel)) {
            return redirect("/");
        }
        return super.view("/add-form");
    }

    @GetMapping("/edit/{virusId}")
    public ModelAndView editView(@PathVariable String virusId) {
        VirusServiceModel serviceModel = this.virusService.getOneById(virusId);
        VirusEditBindingModel editBindingModel =
                this.mapper.map(serviceModel, VirusEditBindingModel.class);

        if (editBindingModel == null) {
            return super.redirect("/viruses/show");
        }

        return this.prepareVirusModel("edit-form",false)
                .addObject("model", editBindingModel);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editAction(@Valid @ModelAttribute("model") VirusEditBindingModel editBindingModel,
                                 final BindingResult bindingResult) {
        if (editBindingModel == null) {
            return super.redirect("/viruses/show");
        }

        if (bindingResult.hasErrors() || !this.virusService.editVirus(editBindingModel)) {
            return this.prepareVirusModel("edit-form", false)
                    .addObject("model", editBindingModel);
        }


        return super.redirect("/viruses/show");
    }

    @GetMapping("/delete/{virusId}")
    public ModelAndView deleteView(@PathVariable String virusId) {
        VirusServiceModel serviceModel = this.virusService.getOneById(virusId);
        VirusDeleteBindingModel deleteBindingModel =
                this.mapper.map(serviceModel, VirusDeleteBindingModel.class);

        if (deleteBindingModel == null) {
            return super.redirect("/viruses/show");
        }

        return this.prepareVirusModel("delete-form",true)
                .addObject("model", deleteBindingModel);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/delete/{id}")
    public ModelAndView deleteAction(@PathVariable String id) {
        if (this.virusService.deleteVirus(id)){
            return this.redirect("/viruses/show");
        }
        return this.redirect("/viruses/show");
    }

    private ModelAndView prepareVirusModel(String formName, boolean showDate){
        return view(formName)
                .addObject("allCapitals",
                        this.capitalService.capitalsNamesList())
                .addObject("showDate", showDate);
    }

    @GetMapping(value = "/all_viruses", produces = "application/json")
    @ResponseBody
    public Object fetchData() {
        List<VirusTableViewModel> allViruses = this.virusService
                .getAllViruses();
        return allViruses;
    }

}
