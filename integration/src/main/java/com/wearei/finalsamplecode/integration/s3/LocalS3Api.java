package com.wearei.finalsamplecode.integration.s3;

import com.wearei.finalsamplecode.integration.annotation.LocalProfile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
@LocalProfile
public class LocalS3Api implements S3Api {

	@Override
	public String uploadImageToS3(MultipartFile file) throws IOException {
		return "";
	}

	@Override
	public void deleteImageFromS3(String imageUrl) {

	}

	@Override
	public String updateImageInS3(String currentImageUrl, MultipartFile newImage) throws IOException {
		return "";
	}
}
