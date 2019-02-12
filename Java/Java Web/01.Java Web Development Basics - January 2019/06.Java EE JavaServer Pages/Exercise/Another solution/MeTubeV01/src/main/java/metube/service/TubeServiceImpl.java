package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repository.interfaces.TubeRepo;
import metube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 20:03 ч.
 */
public class TubeServiceImpl implements TubeService {

    private final TubeRepo tubeRepo;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepo tubeRepo,
                           ModelMapper modelMapper) {
        this.tubeRepo = tubeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper
                .map(tubeServiceModel, Tube.class);
        tube.setYouTubeLink(tubeServiceModel.getYouTubeLink());

        this.tubeRepo.save(tube);
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {

        return this.tubeRepo.findAll()
                .stream()
                .map(tube -> this.modelMapper
                        .map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TubeServiceModel> findTubeByName(String name) {

        Optional<Tube> tube = this.tubeRepo.findByName(name);

        if (tube.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(tube.get(), TubeServiceModel.class));
    }
}