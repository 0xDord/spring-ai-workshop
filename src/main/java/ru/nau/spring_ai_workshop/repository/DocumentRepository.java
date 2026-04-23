package ru.nau.spring_ai_workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nau.spring_ai_workshop.model.LoadedDocument;

public interface DocumentRepository extends JpaRepository<LoadedDocument, Long> {
    boolean existsByFilenameAndContentHash(String filename, String contentHash);
}
