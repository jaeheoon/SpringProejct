package com.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.bean.UserDTO;
import com.user.bean.UserPaging;
import com.user.dao.UserDAO;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging;

	@Override
	public UserDTO findId(String id) {
		return userDAO.findId(id);
	}
	
	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}
	
	@Override
	public Map<String, Object> list(String page) {
		int pg = Integer.parseInt(page);
		int count = 3;
		int startNum = (pg-1) * count;
		int endNum = count;
		int totalNum = userDAO.getTotalNum();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		userPaging.setCurrentPage(pg);
		userPaging.setPageBlock(5);
		userPaging.setPageSize(count);
		userPaging.setTotalA(totalNum);
		userPaging.makePagingHTML();
		List<UserDTO> list = userDAO.list(map);
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("list", list);
		userMap.put("userPaging", userPaging);
		return userMap;
	}
	
	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}
	
	@Override
	public boolean delete(String id, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		UserDTO userDTO = userDAO.idCheck(map);
		if(userDTO != null) {
			userDAO.delete(id);
			return true;
		} else return false;
	}
}
