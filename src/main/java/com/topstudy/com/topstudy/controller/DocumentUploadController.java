package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.service.DocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
public class DocumentUploadController {

    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private DocumentStorageService documentStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            documentStorageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listUploadedFiles() throws IOException {
        return ResponseEntity.ok(documentStorageService.loadAll()
                .map(path ->
                        MvcUriComponentsBuilder.fromMethodName(DocumentUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<String> serveFile(@PathVariable String filename) {
        try {
            Path file = documentStorageService.load(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body("File found and served successfully.");
            } else {
                return ResponseEntity.status(404).body("File not found or is not readable: " + filename);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error serving file: " + e.getMessage());
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        documentStorageService.deleteAll();
        return ResponseEntity.ok("All files have been deleted.");
    }

}
