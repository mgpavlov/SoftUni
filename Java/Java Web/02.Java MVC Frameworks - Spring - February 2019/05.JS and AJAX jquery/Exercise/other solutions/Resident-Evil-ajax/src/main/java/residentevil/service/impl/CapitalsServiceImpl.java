package residentevil.service.impl;

import residentevil.domain.entities.Capitals;
import residentevil.domain.models.service.CapitalsServiceModel;
import residentevil.repository.CapitalsRepository;
import residentevil.service.api.CapitalsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalsServiceImpl implements CapitalsService {

    private final CapitalsRepository capitalsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalsServiceImpl(CapitalsRepository capitalsRepository,
                               ModelMapper modelMapper) {
        this.capitalsRepository = capitalsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CapitalsServiceModel> findAllSortedByName() {
        List<Capitals> capitals = this.capitalsRepository.findAllOrderByName();

        return capitals.stream()
                .map(c -> this.modelMapper.map(c, CapitalsServiceModel.class))
                .collect(Collectors.toList());
    }
}
