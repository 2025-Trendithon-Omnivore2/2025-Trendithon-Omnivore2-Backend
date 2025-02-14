package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application.Image;

import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.exception.ImageUploadFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public String uploadImg(MultipartFile img, Long memberId) throws S3Exception { // 나중에 사용자 구분자 email로 변경하기

        String fileKey = DIR_PATH + "/" + memberId.toString() + img.getOriginalFilename();

        try{
            InputStream inputStream = img.getInputStream();

            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileKey)
                    .contentType(img.getContentType())
                    .build();

            s3Client.putObject(objectRequest, RequestBody.fromInputStream(inputStream, img.getSize()));

            return s3Client.utilities().getUrl(builder -> builder.bucket(bucket)
                    .key(img.getOriginalFilename())).toString();
        } catch(IOException e){
            e.printStackTrace();
        }

        throw new ImageUploadFailException();
    }
}
