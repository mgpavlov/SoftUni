package exam.service;

import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean documentRegister(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);

        return this.documentRepository.registerEntity(document) != null;
    }

    @Override
    public boolean documentDelete(DocumentServiceModel documentServiceModel) {
        return this.documentRepository.deleteEntity(documentServiceModel.getId());
    }

    @Override
    public DocumentServiceModel findDocumentByTitle(String documentTitle) {
        return this.modelMapper.map(this.documentRepository.findByTitle(documentTitle), DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel findDocumentById(String id) {
        return modelMapper.map(this.documentRepository.findById(id), DocumentServiceModel.class);
    }
}
