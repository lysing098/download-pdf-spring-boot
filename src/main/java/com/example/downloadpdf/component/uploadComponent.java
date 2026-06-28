package com.example.downloadpdf.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class uploadComponent {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadComponent(MultipartFile file)throws IOException {
        File dir = new File(uploadDir);

        if(!dir.exists()){
            dir.mkdirs();
        }


        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename() ;

        Path path = Paths.get(uploadDir, fileName);

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        return path.toString();
    }

    public String deleteComponent(String filePath)throws IOException {
        Path path = Paths.get(filePath);

        Files.deleteIfExists(path);

        return "Deleted Successfully";
    }

}
