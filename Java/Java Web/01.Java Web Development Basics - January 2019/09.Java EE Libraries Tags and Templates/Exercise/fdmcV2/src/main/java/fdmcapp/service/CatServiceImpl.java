package fdmcapp.service;

import fdmcapp.domain.entities.Cat;
import fdmcapp.domain.models.service.CatServiceModel;
import fdmcapp.repository.CatRepository;
import org.modelmapper.ModelMapper;


import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCat(CatServiceModel catServiceModel) {
        try {
            this.catRepository.save(this.modelMapper.map(catServiceModel, Cat.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<CatServiceModel> findAllCats() {
        return this.catRepository.findAll()
                .stream()
                .map(e -> this.modelMapper.map(e, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeCat(String id) {
        try {
            this.catRepository.remove(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
