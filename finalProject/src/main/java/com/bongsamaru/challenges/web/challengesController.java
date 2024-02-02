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
import com.bongsamaru.challenges.vo.ChallengesVO;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.file.service.FilesVO;

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
		return "challenge/challengeList";
	}
	
	@GetMapping("/challenge/challengeInfo")
	public String getChallengeInfo(@RequestParam(name="chalId") Integer chalId, Model model,HttpServletRequest request) {
		ChallengesVO vo = challengeService.getChallengeInfo(chalId);
		request.getSession().setAttribute("challengeInfo",vo);
		//model.addAttribute("challengeInfo", vo);
		List<ChallengesVO> file = challengeService.getChallengeFile(chalId);
		model.addAttribute("challengeFile", file);
		return "challenge/challengeInfo";
	}
	
	
	@GetMapping("/challenge/challengesList")
	public String getChallengesList(@RequestParam(name="chalId") Integer chalId, Model model) {
		List<ChallengesVO> dList = challengeService.getChallengesList(chalId);
		model.addAttribute("challengesList", dList);
		return "challenge/challengesList";
	}
	@GetMapping("/challenge/challengeInsert")
	public String insertChallengeForm() {
		return "/challenge/challengeInsert";
	}
	@PostMapping("/challenge/challengeInsert")
	public int getChallengeInsert(ChallengesVO challengeVO) {
		int chalId = challengeService.getChallengeInsert(challengeVO);
		return chalId;
	}
	
	@PostMapping("/challenge/challengesList")
	@ResponseBody
	public int getChallengesInsert(@RequestPart MultipartFile[] uploadFiles,FilesVO fileVO,  String codeNo, ChallengesVO challengeVO) {
		try {
			fileService.uploadFiles(uploadFiles,"p04", codeNo);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		int chalsId = challengeService.getChallengesInsert(challengeVO);
		return chalsId;
	}
	
	
}
