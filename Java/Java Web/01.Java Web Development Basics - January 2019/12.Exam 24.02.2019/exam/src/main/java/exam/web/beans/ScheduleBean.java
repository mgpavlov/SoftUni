package exam.web.beans;

import exam.domain.models.binding.DocumentRegisterBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class ScheduleBean extends BaseBean {

    private DocumentRegisterBindingModel documentRegisterBindingModel;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public ScheduleBean() {
    }

    @Inject
    public ScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.documentRegisterBindingModel = new DocumentRegisterBindingModel();
    }

    public DocumentRegisterBindingModel getDocumentRegisterBindingModel() {
        return documentRegisterBindingModel;
    }

    public void setDocumentRegisterBindingModel(DocumentRegisterBindingModel documentRegisterBindingModel) {
        this.documentRegisterBindingModel = documentRegisterBindingModel;
    }

    public void register() throws IOException {

        try {
            DocumentServiceModel documentServiceModel = this.modelMapper.map(this.documentRegisterBindingModel, DocumentServiceModel.class);
            if (!this.documentService.documentRegister(documentServiceModel)) {
                throw new IllegalArgumentException("Cannot schedule document!");
            }
            String id = this.documentService.findDocumentByTitle(documentServiceModel.getTitle()).getId();

            String details = "/faces/view/details.xhtml?id=" + id;
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(details);
        } catch (Exception ex) {
            this.redirect("schedule");
        }
    }
}
