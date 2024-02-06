package com.bongsamaru.login.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.mypage.mapper.MypageMapper;
import com.bongsamaru.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MypageMapper mypageMapper;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/idCheck/{memId}")
	@ResponseBody
	public Boolean idCheck(@PathVariable String memId) {
		return userService.countMemId(memId);
	}
	
	@GetMapping("/biznum/{bizNum}")
	@ResponseBody
	public Boolean facBizCheck(@PathVariable String bizNum) {
		return userService.countBizNum(bizNum);
	}
	
	@GetMapping("/nickCheck/{memNick}")
	@ResponseBody
	public Boolean idNick(@PathVariable String memNick) {
		return userService.countMemId(memNick);
	}
	
	@PostMapping("/facilitySignUp")
	@ResponseBody
	public ResponseEntity<String> facilitySignUp(@RequestParam(value = "files", required = false) MultipartFile[] files 
											   , @RequestParam("facilityVO") String facilityVO){
		
		ObjectMapper objectMapper = new ObjectMapper();
		FacilityVO vo = new FacilityVO();
		try {
			vo = objectMapper.readValue(facilityVO, FacilityVO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(userService.insertFac(vo)) {
			if(files != null && files.length > 0 ) {
				try {
					fileService.uploadFiles(files,"p02",1,vo.getFacId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return ResponseEntity.ok("회원가입 성공!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
			}
	}
			
	
	
	@PostMapping("/userSignUp")
	@ResponseBody
	public ResponseEntity<String> userSignUp(@RequestParam(value = "files", required = false) MultipartFile[] files 
										   , @RequestParam("userVO") String userVO
										   , @RequestParam(value = "category", required = false) String category){
		
		ObjectMapper objectMapper = new ObjectMapper();
		UserVO vo = new UserVO();
		try {
			vo = objectMapper.readValue(userVO, UserVO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		List<String> categories = new ArrayList<>();
		try {
			categories = objectMapper.readValue(category, new TypeReference<List<String>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if( userService.userInsert(vo, categories ) ) {
			if(files != null && files.length > 0 ) {
				try {
					fileService.uploadFiles(files,"p01",0,vo.getMemId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return ResponseEntity.ok("회원가입 성공!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
		}
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
