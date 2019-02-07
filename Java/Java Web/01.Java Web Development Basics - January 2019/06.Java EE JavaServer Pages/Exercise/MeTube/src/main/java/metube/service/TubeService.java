package metube.service;


import metube.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {

    void saveTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeByName(String name);

    List<TubeServiceModel> findAllTubes();

}
