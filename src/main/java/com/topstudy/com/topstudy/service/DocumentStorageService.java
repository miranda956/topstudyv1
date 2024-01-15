package com.topstudy.com.topstudy.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DocumentStorageService {

    private final Path rootLocation = Paths.get("uploads");

    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to store the file. " + e.getMessage());
        }
    }

    public Stream<Path> loadAll() {
        try {
            return StreamSupport.stream(Files.newDirectoryStream(this.rootLocation).spliterator(), false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files. " + e.getMessage());
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed URL: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


}
