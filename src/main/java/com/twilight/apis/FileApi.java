package com.twilight.apis;

import com.twilight.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileApi {
    @Autowired
    private StorageService storageService;
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file",required = true) MultipartFile file, @RequestParam(name = "folder") String folder) throws IOException {
        return storageService.upload(file,folder);
    }
}
