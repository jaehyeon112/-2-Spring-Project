package com.bongsamaru.mypage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.mypage.service.sendSmsService;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProfileController {
	

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
    // coolSMS 구현 로직 연결  
    @GetMapping("/sendSMS")
    public @ResponseBody String sendSMS(@RequestParam(value="to") String to) throws CoolsmsException {  
        sendSmsService smsService = new sendSmsService();
        return smsService.PhoneNumberCheck(to);
    }
	
}