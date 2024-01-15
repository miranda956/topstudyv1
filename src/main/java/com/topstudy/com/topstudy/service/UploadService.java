package com.topstudy.com.topstudy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${upload.path}")
    private String uploadPath;  // Specify the path where you want to store uploaded files

    public String uploadDocument(MultipartFile file) throws IOException {
        // Generate a unique filename to avoid conflicts
        String originalFilename = file.getOriginalFilename();
        String sanitizedFilename = sanitizeFilename(originalFilename);

        // Resolve the full path where the file will be stored
        Path targetPath = Path.of(uploadPath, sanitizedFilename);

        // Copy the file to the target path
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Return the URL or path where the file is stored
        return "/uploads/" + sanitizedFilename;  // Adjust the URL structure as needed
    }

    private String sanitizeFilename(String filename) {
        // Replace invalid characters with underscores or your preferred replacement
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
    public File getDocument(String filename) {
        // Resolve the full path of the document
        Path documentPath = Path.of(uploadPath, filename);
        return documentPath.toFile();
    }

    public boolean deleteDocument(String filename) {
        // Resolve the full path of the document
        Path documentPath = Path.of(uploadPath, filename);

        try {
            // Delete the file
            Files.deleteIfExists(documentPath);
            return true;
        } catch (IOException e) {
            // Handle the exception, e.g., log it
            return false;
        }
    }
}
