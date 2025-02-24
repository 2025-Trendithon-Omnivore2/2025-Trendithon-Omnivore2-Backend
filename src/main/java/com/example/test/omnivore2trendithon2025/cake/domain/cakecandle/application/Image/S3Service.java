package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application.Image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.exception.ImageUploadFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final String DIR_PATH = "images";

    public String uploadImg(MultipartFile img, String email) throws IOException {

        String fileKey = getFileKey(img, email);
        try(InputStream inputStream = img.getInputStream()){
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(img.getSize());
            metadata.setContentType(img.getContentType());
            PutObjectRequest objectRequest = new PutObjectRequest(bucket, fileKey, inputStream, metadata);
            s3Client.putObject(objectRequest);
        }

        return s3Client.getUrl(bucket, fileKey).toString();
    }

    private String getFileKey(MultipartFile img, String email) {
        String folderName = email.substring(0, email.indexOf("."));

        String uniqueFileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();

        return DIR_PATH + "/" + folderName + "/" + uniqueFileName;
    }
}