package org.softuni.resident_evil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.resident_evil.domain.models.view.CapitalTableViewModel;
import org.softuni.resident_evil.service.CapitalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CapitalController extends BaseController {

    private final CapitalService capitalService;

    private final ModelMapper mapper;

    public CapitalController(CapitalService capitalService, ModelMapper mapper) {
        this.capitalService = capitalService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/all_capitals", produces = "application/json")
    @ResponseBody
    public Object fetchData() {
        List<CapitalTableViewModel> allCapitals = this.capitalService
                .orderedCapitals()
                .stream()
                .map(serviceModel -> this.mapper.map(serviceModel, CapitalTableViewModel.class))
                .collect(Collectors.toList());
        return allCapitals;
    }
}
