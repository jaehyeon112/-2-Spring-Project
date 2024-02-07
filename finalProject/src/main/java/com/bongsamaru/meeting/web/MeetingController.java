package com.bongsamaru.meeting.web;

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
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.meeting.service.MeetingService;

@Controller
public class MeetingController {
	@Autowired
	MeetingService service;
	
	@Autowired
	AdminService userService;
	
	@GetMapping("meetings")
	public String meetings(@RequestParam(name="volId") Integer volId,Model model) {
		service.meetingInfo(volId);
		List<VolActVO> list = service.meetingVolActList(volId);
		model.addAttribute("act",list);
		return "meeting/meetings";
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
}
