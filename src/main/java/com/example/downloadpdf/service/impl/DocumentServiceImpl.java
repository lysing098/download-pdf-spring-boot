package com.example.downloadpdf.service.impl;

import com.example.downloadpdf.component.uploadComponent;
import com.example.downloadpdf.dto.DocumentRequest;
import com.example.downloadpdf.model.Document;
import com.example.downloadpdf.repository.DocumentRepository;
import com.example.downloadpdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private uploadComponent uploadComponent;

    @Override
    public List<Document> getDocuments() {
        var list = documentRepository.findAll();
        return list;
    }

    @Override
    public Document getDocument(long id) {
        return null;
    }

    @Override
    public Document createDocument(DocumentRequest document) throws IOException {


        Document newDocument = new Document();
        newDocument.setTitle(document.getTitle());
        newDocument.setFile(uploadComponent.uploadComponent(document.getFile()));
        documentRepository.save(newDocument);

        return newDocument;

    }

    @Override
    public Document updateDocument(long id, DocumentRequest document) throws IOException {
        var document1 = documentRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Document not found with id: " + id));
        uploadComponent.deleteComponent(document1.getFile());
        document1.setTitle(document.getTitle());
        document1.setFile(uploadComponent.uploadComponent(document.getFile()));
        documentRepository.save(document1);
        return document1;
    }

    @Override
    public Document deleteDocument(long id) throws IOException {
        var document1 = documentRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Document not found with id: " + id));
        uploadComponent.deleteComponent(document1.getFile());
        documentRepository.delete(document1);
        return document1;
    }

    @Override
    public Resource downloadDocument(long id) throws IOException {
        var document = documentRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Document not found"));

        Path path = Paths.get(document.getFile());

        return new FileSystemResource(path);
    }
}
