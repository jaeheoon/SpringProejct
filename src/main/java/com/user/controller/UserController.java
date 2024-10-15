package com.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.bean.UserDTO;
import com.user.bean.UserPaging;
import com.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public String checkId(String id) {
		UserDTO userDTO = userService.findId(id);
		return userDTO != null ? "exist" : "nonExist";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	//required = false - 파라미터가 없어도 에러를 발생시키지 않는다
	//defaultValue = "1" 파라미터가 없으면 기본값 1로 준다
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false, defaultValue = "1") String page, 
					   Model model) {
		Map<String, Object> userMap = userService.list(page);
		
		model.addAttribute("userPaging", (UserPaging)userMap.get("userPaging"));
		model.addAttribute("list", userMap.get("list"));
		model.addAttribute("page", page);
		
		return "/user/list";	// /WEB-INF/user/list.jsp
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public String updateForm(@RequestParam String id, 
						 	 @RequestParam(required = false, defaultValue = "1") String page,
						 	 Model model) {
		
		model.addAttribute("userDTO", userService.findId(id));
		model.addAttribute("page", page);
		
		return "/user/updateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}
	
	@RequestMapping(value = "/deleteForm", method = RequestMethod.GET)
	public String deleteForm(@RequestParam String id, 
						 	 @RequestParam(required = false, defaultValue = "1") String page,
						 	 Model model) {
		model.addAttribute("userDTO", userService.findId(id));
		model.addAttribute("page", page);
		
		return "/user/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam String id, @RequestParam String pwd) {
		boolean check = userService.delete(id, pwd);
		if (check) {
			return "true";
		} else return "false";
	}
	
	//id를 받아서 DTO가 있는지 확인하고 JSON으로 리턴하는 메서드
	/*
	@RequestMapping(value = "getExistPwd")
	public UserDTO getExistPwd(@RequestParam String id, Model model) {
		System.out.println("id : " + id);
		return userService.findId(id);
	}
	*/
}
