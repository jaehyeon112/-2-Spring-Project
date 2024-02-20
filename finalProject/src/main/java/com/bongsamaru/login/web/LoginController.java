package com.bongsamaru.login.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.bongsa.service.BongsaDTO;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.mypage.mapper.MypageMapper;
import com.bongsamaru.mypage.service.DonledgerVO;
import com.bongsamaru.securing.EncryptService;
import com.bongsamaru.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 회원 가입과 관련된 요청을 처리하는 Controller
 * @author 김재현
 *
 */

import lombok.extern.log4j.Log4j2;
@Controller
@Log4j2
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DonaService donaService;

	@Autowired
	AdminService adminService;
	
	@Autowired
	EncryptService encrypt;
	
	@Autowired
	FacilityService facilityService;
	
	@Autowired
	BongsaService bongsaService;
	
	@Autowired
	MypageMapper mypageMapper;
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 
	 * @param memId
	 * @return Boolean
	 */
	@GetMapping("/idCheck/{memId}")
	@ResponseBody
	public Boolean idCheck(@PathVariable String memId) {
		return userService.countMemId(memId);
	}
	
	/**
	 * 
	 * @param bizNum
	 * @return Boolean
	 */
	@GetMapping("/biznum/{bizNum}")
	@ResponseBody
	public Boolean facBizCheck(@PathVariable String bizNum) {
		return userService.countBizNum(bizNum);
	}
	
	/**
	 * 
	 * @param memNick
	 * @return Boolean
	 */
	@GetMapping("/nickCheck/{memNick}")
	@ResponseBody
	public Boolean idNick(@PathVariable String memNick) {
		return userService.countMemId(memNick);
	}
	
	/**
	 * 
	 * @param files
	 * @param facilityVO
	 * @return ResponseEntity
	 */
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
			
	
	/**
	 * 
	 * @param files
	 * @param userVO
	 * @param category
	 * @return ResponseEntity
	 */
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
		
		System.out.println(vo.getMemSsn() + "주민번호는?");
		String ssn = vo.getMemSsn();
		if(vo.getMemSsn() != null && vo.getMemSsn() != "") {
			String encSsn = encrypt.encryptSsn(ssn);
			
			//암호화 된 주민번호
			vo.setMemSsn(encSsn);
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
	
	/**
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/signup")
	public String goToUserSignUp(Model model) {
		List<UserCategoryVO> vo = userService.userCategoty();
		model.addAttribute("category",vo);
		return "login/signup";
	}
	
	@GetMapping("/socialsignup")
	public String goTosocialsignup(HttpSession session, Model model) {
		  String name = (String) session.getAttribute("name");
		  String email = (String) session.getAttribute("email");
		  String sub = (String) session.getAttribute("sub");
		  String profile = (String) session.getAttribute("profile");
		  model.addAttribute("name", name);
		  model.addAttribute("email", email);
		  model.addAttribute("sub", sub);
		  model.addAttribute("profile", profile);
		  List<UserCategoryVO> vo = userService.userCategoty();
		  model.addAttribute("category",vo);
		  
		return "login/SocialSignUp";
	}
	
	@GetMapping("/")
	public String firstPage(Model model, Principal prin) {
		
		boolean isLogin = prin != null;
		model.addAttribute("isLogin",isLogin);
		
		List<DonledgerVO> king = adminService.DonationKing();
		model.addAttribute("king", king);
		
		List<CountVO> volKing = userService.volKing();
		model.addAttribute("vol", volKing);
		
		DonaVO donaVO = new DonaVO();
		List<DonaVO> donaList = donaService.getDonaListByCategory(donaVO);
		model.addAttribute("list", donaList);
		
		List<BongsaDTO> group = bongsaService.getVolTagDTO("e02");
		model.addAttribute("group", group);
		
		List<BongsaDTO> daily = bongsaService.getVolTagDTO("e01");
		model.addAttribute("daily", daily);
		
		List<FacilityVO> facility = facilityService.allFacilityList();
		model.addAttribute("facilityList" , facility);
		
		return "home"; 
	}
	
	
	@GetMapping("/userAlarm")
	@ResponseBody
	public List<AlertVO> getAlerts(Principal principal) {
		List<AlertVO> vo = userService.listAlert(principal.getName());
		log.info(vo);
		return vo;
	}
	
	@GetMapping("/alarmCount")
	@ResponseBody
	public int getAlarm(Principal principal) {
	    // 사용자가 로그인하지 않았을 경우, 즉 principal이 null일 경우 0을 반환
	    if (principal == null) {
	        return 0;
	    }
	    
	    // principal이 null이 아닌 경우, 즉 사용자가 로그인한 상태일 경우
	    String userName = principal.getName(); // 사용자 이름을 가져옴
	    log.info(userName);
	    
	    // 사용자 이름을 기반으로 알림 개수를 조회
	    return userService.countAlarm(userName);
	}
	
	@PostMapping("/updateAlarm")
	@ResponseBody
	public int updateAlarm(@RequestBody AlertVO vo,Principal principal) {
		log.info(vo);
		vo.setReceiveId(principal.getName());
	    userService.updateAlarm(vo);
	    return userService.countAlarm(principal.getName());
	}
	
	
}
