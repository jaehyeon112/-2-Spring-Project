package com.bongsamaru.challenges.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.challenges.mapper.ChallengeMapper;
import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.challenges.vo.ChallengesVO;

@Controller
public class challengesController {
	
	@Autowired
	ChallengesService challengeService;
	ChallengeMapper challengeMapper;
	
	
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
	public int getChallengesInsert(ChallengesVO challengeVO) {
		int chalsId = challengeService.getChallengesInsert(challengeVO);
		return chalsId;
	}
	
	
}
