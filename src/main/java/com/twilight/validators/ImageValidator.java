package com.twilight.validators;

import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.InvalidFileException;
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
    public void validateImages(List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            validateImage(image);
        }
    }
    public void validateImage(MultipartFile file) throws IOException {

        String mimeType =
                tika.detect(file.getInputStream());
        if(file.isEmpty())
            throw new BadRequestException(
                    "User trying to upload file type of this"
                            + file.getOriginalFilename()
                    ,"Invalid file type :"
                    + file.getOriginalFilename());

        if(!ALLOWED_TYPES.contains(mimeType))
            throw new InvalidFileException(
                    "User trying to upload file type of this"
                            + file.getOriginalFilename()
                    ,"Invalid file type :"
                    + file.getOriginalFilename());

    }
}
