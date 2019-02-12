package metube.service.interfaces;

import metube.domain.models.service.TubeServiceModel;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 20:01 ч.
 */
public interface TubeService {

    void saveTube(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> findAllTubes();

    Optional<TubeServiceModel> findTubeByName(String name);
}
