package com.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.user.bean.UserDTO;

@Mapper
public interface UserDAO {
	public UserDTO findId(String id);
	public void write(UserDTO userDTO);
	public List<UserDTO> list(Map<String, Integer> map);
	public int getTotalNum();
	public void update(UserDTO userDTO);
	public UserDTO idCheck(Map<String, String> map);
	public void delete(String id);
}
