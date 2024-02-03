package com.bongsamaru.login.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.common.UserCategoryVO;
import com.bongsamaru.common.UserVO;
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
	
	@GetMapping("signup")
	public String goToUserSignUp(Model model) {
		List<UserCategoryVO> vo = userService.userCategoty();
		System.out.println("이것이 카테고리VO");
		System.out.println(vo);
		model.addAttribute("category",vo);
		
		return "login/signup";
	}
	
	
}
