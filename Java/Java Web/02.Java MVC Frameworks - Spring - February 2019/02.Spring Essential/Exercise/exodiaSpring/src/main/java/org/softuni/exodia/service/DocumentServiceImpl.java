package org.softuni.exodia.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softuni.exodia.domain.entity.Document;
import org.softuni.exodia.domain.model.service.DocumentServiceModel;
import org.softuni.exodia.repository.DocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel registerDocument(DocumentServiceModel documentServiceModel) {
        try {
            Document document = this.modelMapper.map(documentServiceModel, Document.class);
            this.documentRepository.saveAndFlush(document);
            return this.modelMapper.map(document, DocumentServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        return this.documentRepository
                .findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);
        return document == null ? null : this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        this.documentRepository.deleteById(id);
        this.documentRepository.flush();
    }
}
