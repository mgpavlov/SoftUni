package fdmcapp.service;

import fdmcapp.domain.models.service.CatServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface CatService {

    boolean saveCat(CatServiceModel catServiceModel);

    List<CatServiceModel> findAllCats();

    boolean removeCat(String id);
}
