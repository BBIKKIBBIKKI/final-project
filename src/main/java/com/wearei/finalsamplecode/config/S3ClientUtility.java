package com.wearei.finalsamplecode.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Getter
public class S3ClientUtility {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(String dir, MultipartFile multipartFile) {
        try {
            // 파일명을 UUID로 생성
            String fileName = dir + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

            // 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            // 파일을 S3에 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            // 업로드된 파일의 URL 반환
            return amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }
    }

    public void deleteImageFromS3(String imageUrl) {
        // URL에서 파일명 추출
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        amazonS3Client.deleteObject(bucket, fileName);
    }
}
