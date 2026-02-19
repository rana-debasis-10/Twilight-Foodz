package com.twilight.services;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class StorageService {

    private final S3Client s3Client;
    private final String bucket;

    public StorageService(S3Client s3Client, @Value("${minio.client.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    public String upload(MultipartFile file, String folder) throws IOException {

        String key = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        return key;
    }

    public Resource download(String key) {
        byte[] data = s3Client.getObjectAsBytes(
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build()
        ).asByteArray();

        return (Resource) new ByteArrayResource(data);
    }

    public void delete(String key) throws IOException {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build()
        );
    }
}

