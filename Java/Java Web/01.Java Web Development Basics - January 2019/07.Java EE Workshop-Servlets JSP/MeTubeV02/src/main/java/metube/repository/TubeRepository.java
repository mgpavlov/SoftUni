package metube.repository;

import metube.domain.entities.Tube;

import java.util.List;

public interface TubeRepository extends GenericRepository <Tube, String> {

    Tube update(Tube tube);
}
