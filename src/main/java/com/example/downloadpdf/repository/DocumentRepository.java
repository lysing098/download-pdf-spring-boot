package com.example.downloadpdf.repository;

import com.example.downloadpdf.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
