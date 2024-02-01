package com.bongsamaru.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bongsamaru.admin.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/idCheck/{memId}")
	public boolean idCheck(@PathVariable String memId) {
		return userService.countMemId(memId);
	}

}
