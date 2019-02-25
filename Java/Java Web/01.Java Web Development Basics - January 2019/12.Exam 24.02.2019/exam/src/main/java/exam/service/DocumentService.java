package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    boolean documentRegister(DocumentServiceModel documentServiceModel);

    boolean documentUpdate(DocumentServiceModel documentServiceModel);

    boolean documentDelete(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentByTitle(String documentTitle);

    DocumentServiceModel findDocumentById(String id);

    List<DocumentServiceModel> findAllDocuments();
}
