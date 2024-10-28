package com.wearei.finalsamplecode.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3ClientUtility {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3에 이미지를 업로드하는 메소드
    public String uploadImageToS3(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null; // 파일이 없으면 null을 반환
        }

        String fileName = file.getOriginalFilename();
        String fileUrl = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

        return fileUrl;
    }

    // S3에서 이미지를 삭제하는 메소드 (필요 시 사용)
    public void deleteImageFromS3(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }

        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        amazonS3Client.deleteObject(bucket, fileName);
    }

    // S3에서 이미지를 업데이트하는 메소드
    public String updateImageInS3(String currentImageUrl, MultipartFile newImage) throws IOException {
        // 1. 기존 이미지가 있을 경우 삭제
        if (currentImageUrl != null && !currentImageUrl.isEmpty()) {
            deleteImageFromS3(currentImageUrl);
        }

        // 2. 새 이미지를 업로드
        return uploadImageToS3(newImage);
    }
}

