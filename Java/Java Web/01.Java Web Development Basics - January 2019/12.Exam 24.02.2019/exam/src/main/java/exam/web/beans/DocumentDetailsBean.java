package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailViewModel;
import org.modelmapper.ModelMapper;
import exam.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean extends BaseBean{

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentDetailViewModel takeDocument(String id) {
        DocumentServiceModel documentServiceModel =this.documentService.findDocumentById(id);
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return modelMapper.map(documentServiceModel, DocumentDetailViewModel.class);
    }
}
