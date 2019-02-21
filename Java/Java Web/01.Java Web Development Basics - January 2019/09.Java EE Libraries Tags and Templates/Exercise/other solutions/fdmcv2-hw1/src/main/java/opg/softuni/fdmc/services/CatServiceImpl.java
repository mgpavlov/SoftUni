package opg.softuni.fdmc.services;

import opg.softuni.fdmc.domain.dto.CreateCatDto;
import opg.softuni.fdmc.domain.dto.CatViewDto;
import opg.softuni.fdmc.domain.entities.Cat;
import opg.softuni.fdmc.repositories.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
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
    public boolean createCat(CreateCatDto catDto){
        try {
            this.catRepository.save(modelMapper.map(catDto, Cat.class));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCat(String id){
        Cat e = this.catRepository.findById(id);
        try{
            this.catRepository.delete(e);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public List<CatViewDto> listAllCats(){
        return this.catRepository.findAll();
    }



}
