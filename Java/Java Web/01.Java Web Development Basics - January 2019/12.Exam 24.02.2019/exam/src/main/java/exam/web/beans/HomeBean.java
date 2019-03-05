package exam.web.beans;

import exam.domain.models.view.DocumentHomeViewModel;
import org.modelmapper.ModelMapper;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean{

    private List<DocumentHomeViewModel> documents;
    
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {

        this.documents = this.documentService.findAllDocuments().stream()
                .map(d -> this.modelMapper.map(d, DocumentHomeViewModel.class))
                .collect(Collectors.toList());

        this.documents.forEach(d->{
            String title =d.getTitle();
            if (title.length()>12){
                title = d.getTitle().substring(0,12) + "...";
                d.setTitle(title);
            }

        });
    }

    public List<DocumentHomeViewModel> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<DocumentHomeViewModel> documents) {
        this.documents = documents;
    }
}
