package com.bongsamaru.user.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CommonUserController {
	
	
	
	 @GetMapping("/login")
	    public String loginForm(Model model){
	        return "login/FacilityLogin";
	    }
	
	
	
	
}
