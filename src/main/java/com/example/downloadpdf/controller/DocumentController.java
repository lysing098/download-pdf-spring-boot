package com.example.downloadpdf.controller;

import com.example.downloadpdf.dto.DocumentRequest;
import com.example.downloadpdf.model.Document;
import com.example.downloadpdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<Document> getDocument() {
        var document = documentService.getDocuments();
        return document;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createDocument(
            @ModelAttribute DocumentRequest document
    ) throws IOException {

        var result = documentService.createDocument(document);

        return ResponseEntity.ok(result);
    }


    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateDocument(@PathVariable Long id, @ModelAttribute DocumentRequest document) throws IOException {
        var result = documentService.updateDocument(id, document);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable long id) throws IOException {
        var result = documentService.deleteDocument(id);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable Long id
    ) throws IOException {

        Resource resource = documentService.downloadDocument(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + resource.getFilename()
                                + "\""
                )
                .body(resource);
    }
}