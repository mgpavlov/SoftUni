package fdmcapp.web.mbeans;

import fdmcapp.domain.models.view.AllCatsViewModel;
import fdmcapp.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllCatsBean {

    private List<AllCatsViewModel> allCats;

    private CatService catService;
    private ModelMapper modelMapper;

    public AllCatsBean() {
    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.allCats = this.catService.findAllCats()
                .stream()
                .map(e -> this.modelMapper.map(e, AllCatsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<AllCatsViewModel> getAllCats() {
        return this.allCats;
    }

    public void setAllCats(List<AllCatsViewModel> allCats) {
        this.allCats = allCats;
    }
}
