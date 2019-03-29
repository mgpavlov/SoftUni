package residentevil.controllers;

import residentevil.domain.models.view.CapitalsListViewModel;
import residentevil.domain.models.view.VirusListViewModel;
import residentevil.service.api.CapitalsService;
import residentevil.service.api.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShowPageRestController {
    private final CapitalsService capitalsService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShowPageRestController(CapitalsService capitalsService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalsService = capitalsService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/show-capitals")
    public ResponseEntity<?> showCapitalTable(@RequestBody String data) {
        String dataFromClient=data;
        if(!data.equalsIgnoreCase("capitals")) {


            return new ResponseEntity<>(new Exception("Something went wrong!"), HttpStatus.OK);
        }
        List<CapitalsListViewModel> capitals = this.capitalsService.findAllSortedByName()
                .stream().map(capitalsServiceModel -> this.modelMapper.map(capitalsServiceModel, CapitalsListViewModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(capitals, HttpStatus.OK);
    }

    @PostMapping(value = "/show-viruse-table")
    public ResponseEntity<?> showVirusesTable(@RequestBody String data) {
        String dataFromClient=data;
        if(!data.equalsIgnoreCase("viruses")) {


            return new ResponseEntity<>(new Exception("Something went wrong!"), HttpStatus.OK);
        }
        List<VirusListViewModel> viruses = this.virusService.findAllViruses()
                .stream().map(virusServiceModel -> this.modelMapper.map(virusServiceModel, VirusListViewModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(viruses, HttpStatus.OK);
    }

}
