package com.twilight.storages;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ObjectStorage {

    public String upload(MultipartFile file, String folder) throws IOException;

    Resource download(String key);

    void delete(String key) throws IOException;
}

