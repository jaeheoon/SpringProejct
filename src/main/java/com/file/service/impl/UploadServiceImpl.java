package com.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.bean.UploadDTO;
import com.file.dao.UploadDAO;
import com.file.service.UploadService;
import com.user.service.ObjectStorageService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	private UploadDAO uploadDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-147";

	@Override
	public void imageWrite(List<UploadDTO> list) {
		uploadDAO.imageWrite(list);
	}

	@Override
	public List<UploadDTO> imageList() {
		return uploadDAO.imageList();
	}

	@Override
	public UploadDTO getImage(String seq) {
		return uploadDAO.getImage(seq);
	}
	
	@Override
	public void imageDelete(List<String> list) {
		for (String seq : list) {
			String imageFileName = uploadDAO.getImageFileName(Integer.parseInt(seq));
			objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
		}
		uploadDAO.imageDelete(list);
	}

	@Override
	public void imageUpdate(UploadDTO uploadDTO, MultipartFile img) {
		//실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		
		//Object Storage(NCP)는 이미지를 덮어쓰지 않는다.
		//DB에서 seq에 해당하는 imageFileName을 꺼내와서 Object Storage(NCP)에 이미지를 삭제하고 재등록해준다.
		UploadDTO imageFileDTO = uploadDAO.getImage(uploadDTO.getSeq()+"");
		String imageFileName = imageFileDTO.getImageFileName();
		String originalFileName;
		
		if (img.getSize() != 0) {
			//삭제
			objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
			//추가
			imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
			originalFileName = img.getOriginalFilename();
			File file = new File(filePath, originalFileName);
			
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			imageFileName = imageFileDTO.getImageFileName();
			originalFileName = imageFileDTO.getImageOriginalFileName();
		}
		
		uploadDTO.setImageFileName(imageFileName);
		uploadDTO.setImageOriginalFileName(originalFileName);
		
		//DB
		uploadDAO.imageUpdate(uploadDTO);
	}
	
}
