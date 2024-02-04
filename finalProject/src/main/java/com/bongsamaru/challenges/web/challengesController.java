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
		//System.out.println(dList);
		model.addAttribute("challengesList", dList);
		return "challenge/challengesList";
	}
	//등록페이지 열기
	@GetMapping("/challenge/challengeInsert")
	public String insertChallengeForm() {
		return "challenge/challengeInsert";
	}
	
	//게시글등록
	@PostMapping("/challenge/challengeInsert")
	@ResponseBody
	public String getChallengeInsert(@RequestPart MultipartFile[] uploadFiles, ChallengesVO challengeVO) {
		challengeService.getChallengeInsert(challengeVO);
		String chalId = challengeVO.getCodeNo();
		try {
			fileService.uploadFiles(uploadFiles,"p03", chalId);
		} catch (IOException e) {
			e.printStackTrace();
		}
			return chalId;
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
			fileService.uploadFiles(uploadFiles,"p04", chalDetId.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chalDetId;
	}
	
	
}
