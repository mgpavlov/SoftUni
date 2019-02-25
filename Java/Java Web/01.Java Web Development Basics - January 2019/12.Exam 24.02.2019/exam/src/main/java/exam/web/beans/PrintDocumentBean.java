package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class PrintDocumentBean extends BaseBean{

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public PrintDocumentBean() {
    }

    @Inject
    public PrintDocumentBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentDetailViewModel takeDocument(String id) {
        DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return modelMapper.map(documentServiceModel, DocumentDetailViewModel.class);
    }

    public void printDocument() {
        String id = ((HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext()
                .getRequest()).getParameter("documentId");

        DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);

        if (!this.documentService.documentDelete(documentServiceModel)) {
            throw new IllegalArgumentException("Cannot delete document application!");
        }
        this.redirect("home");
    }
}
