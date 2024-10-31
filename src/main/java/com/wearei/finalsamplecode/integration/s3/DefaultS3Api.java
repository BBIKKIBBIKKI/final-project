package com.wearei.finalsamplecode.integration.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.wearei.finalsamplecode.common.annotation.AppProfile;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@Component
@AppProfile
@RequiredArgsConstructor
public class DefaultS3Api implements S3Api {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3에 이미지를 업로드하는 메소드
    @Override
    public String uploadImageToS3(MultipartFile file) throws IOException {
        if (Objects.isNull(file)) {
            return null; // 파일이 없으면 null을 반환
        }

        String fileName = file.getOriginalFilename();
        String prefix = String.valueOf(System.currentTimeMillis());
        String newFileName = String.format("%s_%s", prefix, fileName);
        String fileUrl = String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", bucket, newFileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, newFileName, file.getInputStream(), metadata);

        return fileUrl;
    }

    // S3에서 이미지를 삭제하는 메소드 (필요 시 사용)
    @Override
    public void deleteImageFromS3(String imageUrl) {
        if (Strings.isBlank(imageUrl)) {
            return;
        }

        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        amazonS3Client.deleteObject(bucket, fileName);
    }

    // S3에서 이미지를 업데이트하는 메소드
    @Override
    public String updateImageInS3(String currentImageUrl, MultipartFile newImage) throws IOException {
        // 1. 기존 이미지가 있을 경우 삭제
        if (!Strings.isBlank(currentImageUrl)) {
            deleteImageFromS3(currentImageUrl);
        }

        // 2. 새 이미지를 업로드
        return uploadImageToS3(newImage);
    }
}