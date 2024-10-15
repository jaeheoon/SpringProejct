package com.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.file.bean.UploadDTO;

public interface UploadService {
//	public void imageWrite(UploadDTO uploadDTO);
	public void imageWrite(List<UploadDTO> list);
	public List<UploadDTO> imageList();
	public UploadDTO getImage(String seq);
	public void imageUpdate(UploadDTO uploadDTO, MultipartFile img);
	public void imageDelete(List<String> list);
}
