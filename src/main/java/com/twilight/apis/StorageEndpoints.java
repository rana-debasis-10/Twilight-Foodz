package com.twilight.apis;

import com.twilight.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageEndpoints {
    @Autowired
    StorageService storageService;

    @Cacheable
    @GetMapping("/download")
    public ResponseEntity<Resource> download(
            @RequestParam String key) {
        Resource resource = storageService.download(key);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + key + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
