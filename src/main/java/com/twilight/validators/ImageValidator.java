package com.twilight.validators;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class ImageValidator {
    @Autowired
    Tika tika ;

    private final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );
    public boolean validateImages(List<MultipartFile> images) throws IOException , IllegalArgumentException{
        Tika tika = new Tika();
        for (MultipartFile file : images) {

            String mimeType =
                    tika.detect(file.getInputStream());

            if (!ALLOWED_TYPES.contains(mimeType)) {
                return false;
            }
        }
        return true;
    }
    public boolean validateImage(MultipartFile file) throws IOException {
        String mimeType =
                tika.detect(file.getInputStream());

        if (!ALLOWED_TYPES.contains(mimeType)) {
            return false;
        }
        return true;
    }
}
