package org.softuni.exodia.repository;

import org.softuni.exodia.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
}
