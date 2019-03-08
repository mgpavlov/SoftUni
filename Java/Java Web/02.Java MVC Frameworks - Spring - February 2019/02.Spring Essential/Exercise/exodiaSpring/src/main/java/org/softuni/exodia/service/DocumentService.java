package org.softuni.exodia.service;

import org.softuni.exodia.domain.model.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel registerDocument(DocumentServiceModel documentServiceModel);

    List<DocumentServiceModel> findAll();

    DocumentServiceModel findById(String id);

    void deleteById(String id);
}
