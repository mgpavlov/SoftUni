package opg.softuni.fdmc.repositories;

import opg.softuni.fdmc.domain.dto.CatViewDto;
import opg.softuni.fdmc.domain.entities.Cat;

import java.util.List;

public interface CatRepository extends Repository<String, Cat> {
    List<CatViewDto> findAll();
}
