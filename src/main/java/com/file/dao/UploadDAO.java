package com.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.file.bean.UploadDTO;

@Mapper
public interface UploadDAO {
//	public void imageWrite(UploadDTO uploadDTO);
	public void imageWrite(List<UploadDTO> list);
	public List<UploadDTO> imageList();
	public UploadDTO getImage(String seq);
	public void imageUpdate(UploadDTO uploadDTO);
	public String getImageFileName(int seq);
	public void imageDelete(List<String> list);
}
