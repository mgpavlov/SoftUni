package opg.softuni.fdmc.services;

import opg.softuni.fdmc.domain.dto.CreateCatDto;
import opg.softuni.fdmc.domain.dto.CatViewDto;

import java.util.List;

public interface CatService {
    boolean createCat(CreateCatDto employeeDto);

    boolean deleteCat(String id);

    List<CatViewDto> listAllCats();

}
