package com.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.file.bean.UploadDTO;
import com.file.service.UploadService;
import com.user.service.ObjectStorageService;

@Controller
@RequestMapping(value = "/file")
public class UploadController {
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-147";
	
	@RequestMapping(value = "/uploadAJaxForm")
	public String uploadAJaxForm() {
		return "/upload/uploadAJaxForm";
	}
	
	@RequestMapping(value = "/uploadForm")
	public String uploadForm() {
		return "/upload/uploadForm";
	}
	
	@RequestMapping(value = "/uploadView")
	public String uploadView(@RequestParam String seq, Model model) {
		model.addAttribute("uploadDTO", uploadService.getImage(seq));
		return "/upload/uploadView";
	}
	
	@RequestMapping(value = "/updateForm")
	public String updateForm(@RequestParam String seq, Model model) {
		model.addAttribute("uploadDTO", uploadService.getImage(seq));
		return "/upload/uploadUpdateForm";
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(@ModelAttribute UploadDTO uploadDTO, 
						 @RequestParam("img") MultipartFile img) {
		uploadService.imageUpdate(uploadDTO, img);
		return "";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(@RequestBody Map<String, List<String>> seqs) {
		List<String> seqList = seqs.get("seqs");
	    // seqList에 담긴 seq 값들을 기반으로 삭제 로직 구현
    	uploadService.imageDelete(seqList);
		return "ok";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("list", uploadService.imageList());
		return "/upload/uploadList";
	}
	
	//1개 또는 여러개
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UploadDTO uploadDTO, @RequestParam("img[]") List<MultipartFile> list, HttpSession session) {
		//실제 폴더 
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 = " + filePath);
		
		String originalFileName;
		File file;
		StringBuilder result = new StringBuilder();
		
		//파일들을 모아서 DB로 보내기
		List<UploadDTO> uploadList = new ArrayList<UploadDTO>();
		for (MultipartFile img : list) {
			originalFileName = img.getOriginalFilename();
			file = new File(filePath, originalFileName);
			String imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
			
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.append("<span>")
				  .append("<img src='/spring/storage/")
				  .append(originalFileName)
				  .append("' width='300' height='300'>")
				  .append("</span>");
			
			UploadDTO dto = new UploadDTO();
			dto.setImageName(uploadDTO.getImageName());
			dto.setImageContent(uploadDTO.getImageContent());
			
//			dto.setImageFileName(UUID.randomUUID().toString());
//			Naver Cloud PlateForm ObjectStorage 사용
			dto.setImageFileName(imageFileName);
			
			dto.setImageOriginalFileName(originalFileName);
			
			uploadList.add(dto);
		}
		
//		한개씩 여러번 넣을때
//		for (UploadDTO uploadDTO2 : uploadList) {
//			uploadService.imageWrite(uploadDTO2);
//		}
		uploadService.imageWrite(uploadList);
		
		return result.toString();
	}
	
	/* 다중 파일 업로드 */
	/*
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@ModelAttribute UploadDTO uploadDTO, @RequestParam MultipartFile[] img, HttpSession session) {

		//실제 폴더 
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 = " + filePath);
		
		String originalFileName;
		File file;
		StringBuilder result = new StringBuilder();
		
		//------------------------------------------------------------------ 
		
		originalFileName = img[0].getOriginalFilename();
		file = new File(filePath, originalFileName);

		try {
			img[0].transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result.append("<span>").append("<img src='/spring/storage/").append(originalFileName)
				.append("' width='300' height='300'>").append("</span>");
		uploadDTO.setImageOriginalFileName(originalFileName);
	
		//------------------------------------------------------------------ 
		
		originalFileName = img[1].getOriginalFilename();
		file = new File(filePath, originalFileName);
		
		try {
			img[0].transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result.append("<span>").append("<img src='/spring/storage/").append(originalFileName)
		.append("' width='300' height='300'>").append("</span>");
		uploadDTO.setImageOriginalFileName(originalFileName);

		//------------------------------------------------------------------
		
		UUID.randomUUID();
		
		System.out.println(result.toString());

		return result.toString();
	}
	*/

	
	 /*사진 값이 한 개만 들어올 때*/
	/*
	 @RequestMapping(value = "/upload", method = RequestMethod.POST)
	 @ResponseBody 
	 public String upload(@ModelAttribute UploadDTO uploadDTO, @RequestParam MultipartFile img, HttpSession session) {
		 //실제 폴더 
		 String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		 System.out.println("실제 폴더 = " + filePath);
		 
		 String originalFileName = img.getOriginalFilename();
		 
		 File file = new File(filePath, originalFileName);
		 
		 try { 
			 img.transferTo(file); 
		 } catch (IllegalStateException e) {
			 e.printStackTrace(); 
		 } catch (IOException e) { 
			 e.printStackTrace(); 
		 }
		 
		 StringBuilder result = new StringBuilder();
		 result.append("<span>")
		 	   .append("<img src='/spring/storage/")
		 	   .append(originalFileName)
		 	   .append("' width='300' height='300'>")
		 	   .append("</span>");
		 
		 uploadDTO.setImageOriginalFileName(originalFileName);
		 
		 UUID.randomUUID();
		 
		 return result.toString(); 
	 }
	 */
	 
}
