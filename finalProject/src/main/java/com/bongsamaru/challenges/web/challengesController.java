package com.bongsamaru.challenges.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.file.service.FileService;


@Controller
public class challengesController {
	
	@Autowired
	ChallengesService challengeService;
	@Autowired
    private FileService fileService;
	
	
	@GetMapping("/challengeList")
	public String getChallengeList(Model model) {
		List<ChallengesVO> list = challengeService.getChallengeList();
		model.addAttribute("challengeList", list);
		System.out.println("목록에서 count가 나오느냐" +list);
		return "challenge/challengeList";
	}
	
	@GetMapping("/challenge/challengeInfo")
	public String getChallengeInfo(@RequestParam(name="chalId") Integer chalId, Model model,HttpServletRequest request) {
		ChallengesVO vo = challengeService.getChallengeInfo(chalId);
		request.getSession().setAttribute("challengeInfo",vo);
		System.out.println("session"+ vo);
		 List<ChallengesVO> file = challengeService.getFileList(vo.getChalId(),"p03", vo.getChalId(),null);
		 model.addAttribute("FileList", file); System.out.println("파일이 뭐가 들어오나"+file);
		  return "challenge/challengeInfo";
		 
	}
	
	
	@GetMapping("/challenge/challengesList")
	public String getChallengesList(@RequestParam(name="chalId") Integer chalId, Model model) {
		List<ChallengesVO> dList = challengeService.getChallengesList(chalId);
	    
	    for (ChallengesVO challengesVO : dList) {
	        challengesVO.getChalDetId();
	        System.out.println("for문 돌린 vo"+challengesVO);
	        System.out.println( "챌린지도전 잘 들어오나?"+ challengesVO.getChalDetId());
	        System.out.println(chalId);
	        List<ChallengesVO> files = challengeService.getFileList(challengesVO.getChalDetId(), "p04",  null, challengesVO.getChalDetId());
	        //challengesVO.getFile().setFilePath(files.get(0).getFile().getFilePath());
	       System.out.println(files);
	        //challengesVO.setFilePath(files.get(0).getFilePath());
	        System.out.println(challengesVO);
	        
	    }
	    model.addAttribute("challengesList", dList);
		System.out.println("총결론"+dList);
		 return "challenge/challengesList";
	}
	//등록페이지 열기
	@GetMapping("/challengeInsert")
	public String insertChallengeForm() {
		return "challenge/challengeInsert";
	}
	
	//게시글등록

	@PostMapping("/challenge/challengeInsert")
	@ResponseBody
	public String getChallengeInsert(@RequestPart MultipartFile[] uploadFiles, ChallengesVO challengeVO) {
		challengeService.getChallengeInsert(challengeVO);
		int chalId = challengeVO.getFile().getCodeNo();
		try {
			fileService.uploadFiles(uploadFiles,"p03", chalId,challengeVO.getMemId());

		} catch (IOException e) {
			e.printStackTrace();
		}
			//return chalId;
			return "redirect:challengeList";
	}
	
	//게시글 참여
	@PostMapping("/challenge/challengeDeInsert")
	@ResponseBody	
	public Integer getChallengesInsert (@RequestPart MultipartFile[] uploadFiles, ChallengesVO challengeVO) {
		challengeVO.setMemId("maru0505");
		challengeService.getChallengesInsert(challengeVO);
		System.out.println("챌린지참여"+challengeVO);
		Integer chalDetId = challengeVO.getChalDetId();
		
		try {
			fileService.uploadFiles(uploadFiles,"p04", chalDetId, challengeVO.getMemId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chalDetId;
	}
	
	
}
