package com.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {

	public String uploadFile(String bucketName, String directoryPath, MultipartFile img);

	public void deleteFile(String bucketName, String directoryPath, String imageFileName);
	
}
