package com.bongsamaru.challenges.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.challenges.vo.ChallengesVO;

@Controller
public class challengesController {
	
	@Autowired
	ChallengesService challengeService;
	
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
		System.out.println(dList);
		return "challenge/challengesList";
	}
}
