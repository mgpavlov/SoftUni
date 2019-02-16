package fdmcapp.web.mbeans;

import fdmcapp.service.CatService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatRemoveBean {
    private CatService catService;

    public CatRemoveBean() {
    }

    @Inject
    public CatRemoveBean(CatService catService) {
        this.catService = catService;
    }


    public void remove(String id) throws IOException {
        this.catService.removeCat(id);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/view/all-cats.xhtml");
    }
}
