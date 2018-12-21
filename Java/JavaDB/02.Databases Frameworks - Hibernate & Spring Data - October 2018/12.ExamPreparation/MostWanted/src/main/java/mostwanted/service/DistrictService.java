package mostwanted.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface DistrictService {

    Boolean districtsAreImported();

    String readDistrictsJsonFile() throws IOException;

    String importDistricts(String districtsFileContent);
}
