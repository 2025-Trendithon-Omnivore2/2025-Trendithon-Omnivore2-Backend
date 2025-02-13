package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application.Image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final String DIR_PATH = "/images";

    public String uploadImg(MultipartFile img, Long memberId) throws IOException {
        Path tempFile = Paths.get("uploads", img.getOriginalFilename());

        Files.createDirectories(tempFile.getParent());
        img.transferTo(tempFile.toFile());

        String fileKey = DIR_PATH + "/" + memberId.toString() + "/" + img.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                                        .bucket(bucket)
                                        .key(fileKey)
                                        .build();

        s3Client.putObject(objectRequest, RequestBody.fromFile(tempFile.toFile()));

        return s3Client.utilities().getUrl(b -> b.bucket(bucket).key(fileKey)).toString();
    }
}
