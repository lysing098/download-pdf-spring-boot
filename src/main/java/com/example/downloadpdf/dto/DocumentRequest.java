package com.example.downloadpdf.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DocumentRequest {
    private String title;
    private MultipartFile file;
}
