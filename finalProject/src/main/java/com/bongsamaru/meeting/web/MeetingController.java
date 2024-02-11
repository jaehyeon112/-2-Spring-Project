package com.bongsamaru.meeting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.meeting.service.MeetingService;

@Controller
public class MeetingController {
	@Autowired
	MeetingService service;
	
	@Autowired
	AdminService userService;
	
	//모임 방 메인
	@GetMapping("meetings")
	public String meetings(@RequestParam(name="volId") Integer volId,Model model) {
		//service.meetingInfo(volId);
		List<VolActVO> list = service.meetingVolActList(volId);
		model.addAttribute("act",list);
		List<VolMemVO> member = service.meetingMemList(volId);
		model.addAttribute("member",member);
		List<VolMemVO> cnt = service.volCnt(volId);
		model.addAttribute("cnt",cnt);
		Date today = new Date();
		model.addAttribute("today",today);
		List<VolActVO> after = new ArrayList<>();
		List<VolActVO> before = new ArrayList<>();
		
		for(VolActVO vo : list) {
			if(vo.getVolDate().compareTo(today) >= 0) {
				after.add(vo);
			}else {
				before.add(vo);
			}
		}
		model.addAttribute("after",after);
		model.addAttribute("before",before);
		return "meeting/meetings";
	}
	
	@GetMapping("volActMemCnt")
	@ResponseBody
	public VolActVO volActMemCnt(@RequestParam(name="volActId") Integer volActId) {
		return service.volActMemCnt(volActId);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("meetingInfo")
	@ResponseBody
	public VolunteerVO meetingInfo(@RequestParam(name="volId") Integer volId) {
		return service.meetingInfo(volId);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("meetingTag")
	@ResponseBody
	public List<TagVO> meetingTag() {
		return userService.tagList();
	}
	
	@GetMapping("meeting")
	public String meeting() {
		return "meeting/meeting";
	}
	
	@GetMapping("meetingInfoPage")
	public String meetingInfo(@RequestParam(name="volId") Integer volId,Model model) {
		VolunteerVO vo = service.meetingInfo(volId);
		model.addAttribute("info",vo);
		return "meeting/meetingInfo";
	}
	
	
}
