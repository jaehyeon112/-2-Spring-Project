package com.bongsamaru.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.admin.service.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("userList")
	public String getUserList(Model model) {
		List<UserVO> list = userService.userList();
		model.addAttribute("userList",list);
		return "admin/userList";
	}
}
