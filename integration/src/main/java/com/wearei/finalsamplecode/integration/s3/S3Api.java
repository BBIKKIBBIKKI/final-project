package com.wearei.finalsamplecode.integration.s3;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Api {
	String uploadImageToS3(MultipartFile file) throws IOException;
	void deleteImageFromS3(String imageUrl) throws IOException;
	String updateImageInS3(String currentImageUrl, MultipartFile newImage) throws IOException;
}
