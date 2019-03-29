package residentevil.service.api;

import residentevil.domain.models.service.CapitalsServiceModel;

import java.util.List;

public interface CapitalsService {

    List<CapitalsServiceModel> findAllSortedByName();
}
