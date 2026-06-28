package com.example.downloadpdf.service;

import com.example.downloadpdf.dto.DocumentRequest;
import com.example.downloadpdf.model.Document;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    List<Document> getDocuments();
    Document getDocument(long id);
    Document createDocument(DocumentRequest document) throws IOException;
    Document updateDocument(long id,DocumentRequest document) throws IOException;
    Document deleteDocument(long id) throws IOException;
    Resource downloadDocument(long id) throws IOException;


}
