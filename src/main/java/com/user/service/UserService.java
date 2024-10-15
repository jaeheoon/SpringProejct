package com.user.service;

import java.util.Map;

import com.user.bean.UserDTO;

public interface UserService {
	public UserDTO findId(String id);
	public void write(UserDTO userDTO);
	public Map<String, Object> list(String page);
	public void update(UserDTO userDTO);
	public boolean delete(String id, String pwd);
}
