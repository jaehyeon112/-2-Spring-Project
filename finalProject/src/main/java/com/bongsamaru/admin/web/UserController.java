package com.bongsamaru.admin.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.admin.service.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("userList")
	public String getEmplList(@RequestParam(name="memStat") String memStat,Model model) {
		System.out.println(memStat);
		UserVO vo = userService.getUserList(memStat);
		model.addAttribute("userList",vo);
		System.out.println(vo);
		return "admin/userList";
	}
}
