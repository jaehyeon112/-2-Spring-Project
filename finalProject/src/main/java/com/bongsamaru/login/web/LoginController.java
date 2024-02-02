package com.bongsamaru.login.web;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.user.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/idCheck/{memId}")
	@ResponseBody
	public Boolean idCheck(@PathVariable String memId) {
		return userService.countMemId(memId);
	}
	
	@GetMapping("/nickCheck/{memNick}")
	@ResponseBody
	public Boolean idNick(@PathVariable String memNick) {
		return userService.countMemId(memNick);
	}
	
	@PostMapping("/userSignUp")
	@ResponseBody
	public Map<String, Object> userSignUp(@RequestBody UserVO vo) {
		System.out.println(vo);
		
		 Map<String, Object> result = new HashMap<>();
		 Boolean type = userService.insertUser(vo);
		 if(type) {
			result.put("result", "success"); 
		 }
		 result.put("result", "error"); 
		
		return result;
	}
	
}
