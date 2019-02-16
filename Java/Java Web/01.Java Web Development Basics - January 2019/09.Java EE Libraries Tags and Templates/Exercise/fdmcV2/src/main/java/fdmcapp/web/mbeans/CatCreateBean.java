package fdmcapp.web.mbeans;

import fdmcapp.domain.entities.Cat;
import fdmcapp.domain.models.binding.CatCreateBindingModel;
import fdmcapp.domain.models.service.CatServiceModel;
import fdmcapp.service.CatService;
import fdmcapp.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;
    private ValidationUtil validationUtil;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return this.catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void createCat() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(!this.validationUtil.validate(this.modelMapper.map(this.catCreateBindingModel, Cat.class))){
            context.redirect("/view/create-cat.xhtml");
        }
        this.catService
                .saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        context.redirect("/view/all-cats.xhtml");
    }
}
