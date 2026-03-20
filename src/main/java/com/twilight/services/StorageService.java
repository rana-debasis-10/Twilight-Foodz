package com.twilight.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface StorageService {

    public String upload(MultipartFile file, String folder) throws IOException;
    public Resource download(String key) ;
    public void delete(String key) throws IOException ;
}

