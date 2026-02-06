package com.twilight.storages.fileSystem;

import com.twilight.storages.ObjectStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


@Service
@Profile("local")
public class FileSystemStorage implements ObjectStorage {

    @Value("${storages.base-path}")
    private String basePath;

    @Override
    public String upload(MultipartFile file, String folder) throws IOException {

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("Only images allowed");
        }

        String key = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path target = Paths.get(basePath, key);

        Files.createDirectories(target.getParent());
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return key; // store this in DB
    }

    @Override
    public Resource download(String key) {
        return new FileSystemResource(Paths.get(basePath, key));
    }

    @Override
    public void delete(String key) throws IOException{
        try{
            Files.deleteIfExists(Paths.get(basePath, key));
        }
        catch (Exception e){
            System.out.println("Now");
        }
    }
}

