package com.bongsamaru.meeting.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.PageVO;
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
	/**
	 * 모임 방 메인
	 * @param volId
	 * @param model
	 * @param req
	 * @param prin
	 * @return
	 */
	@GetMapping("meetings")
	public String meetings(@RequestParam(name="volId") Integer volId,Model model,HttpServletRequest req,Principal prin,VolunteerVO volunteerVO) {
		 if(prin != null && prin.getName() != null) {
	        model.addAttribute("userId",prin.getName());
	    } else {
	        System.out.println("User is not logged in.");
	    }

		VolunteerVO vo2 = service.meetingInfo(volId);
		model.addAttribute("info",vo2);
		List<VolActVO> list = service.meetingVolActList(volId);
		model.addAttribute("act",list);
		List<VolMemVO> member = service.meetingMemList(volId);
		model.addAttribute("member",member);
		List<VolMemVO> cnt = service.volCnt(volId);
		model.addAttribute("cnt",cnt);
		Date today = new Date();
		model.addAttribute("today",today);
		List<VolActVO> review = service.volActReviewList(volId);
		for(VolActVO vo : review) {
			vo.setFilePath(service.findFile("p12",vo.getVolActId()));
		}
		
		model.addAttribute("review",review);
		List<VolActVO> after = new ArrayList<>();
		List<VolActVO> before = new ArrayList<>();
		req.getSession().setAttribute("id",volId);
		
		for(VolActVO vo : list) {
			vo.setFilePath(service.findFile("p11",vo.getVolActId()));
			if(vo.getVolDate().compareTo(today) >= 0) {
				after.add(vo);
			}else {
				before.add(vo);
			}
		}
		model.addAttribute("after",after);
		model.addAttribute("before",before);
		
		volunteerVO.setRoomStat(1);
		List<VolunteerVO> randomList = userService.meetingList(volunteerVO);
		model.addAttribute("choose",randomList);
		
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
	
	//레이아웃에서 아작스로 받기
	@GetMapping("findMember")
	@ResponseBody
	public int findMember(@RequestParam(name="volId") Integer volId,@RequestParam(name="memId",required = false) String memId) {
		return service.findMember(volId,memId);
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
	
	//봉사게시판
	@GetMapping("volBoardList")
	public String volBoardList(PageVO pvo,@RequestParam(name="volId") Integer volId,Model model,HttpServletRequest req
								, @RequestParam(value="searchKeyword", required = false)String searchKeyword
							 	, @RequestParam(value="category", required = false)String category
								, @RequestParam(value="start", required = false)String start
								, @RequestParam(value="end", required = false)String end) {
		
		int total = service.meetingVolActListCnt(volId);
		int startPage = (start == null) ? 1 : Integer.parseInt(start);
        int endPage = (end == null) ? 10 : Integer.parseInt(end);
		
        if(searchKeyword == null) {
        	pvo = new PageVO(total, startPage, endPage, category);	            	
        }else {
        	pvo = new PageVO(total, startPage, endPage, category,searchKeyword);
        }
        
		List<VolActVO> list = service.meetingVolActList(volId);
		req.getSession().setAttribute("id",volId);
		model.addAttribute("vo",pvo);
     	model.addAttribute("category",category);
		model.addAttribute("act",list);
		Date today = new Date();
		for(VolActVO vo : list) {
			if(vo.getVolDate().compareTo(today) >= 0) {
				vo.setState(1);
			}else {
				vo.setState(0);
			}
		}
		return "meeting/volBoardList";
	}
	
	//자유게시판
	@GetMapping("freeBoardList")
	public String freeBoardList(PageVO vo, Model model
							 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
							 	, @RequestParam(value="category", required = false)String category
								, @RequestParam(value="start", required = false)String start
								, @RequestParam(value="end", required = false)String end) {
		int total = userService.getBoardCnt(vo);
		int startPage = (start == null) ? 1 : Integer.parseInt(start);
        int endPage = (end == null) ? 10 : Integer.parseInt(end);
        
        if(searchKeyword == null) {
        	vo = new PageVO(total, startPage, endPage, category);	            	
        }else {
        	vo = new PageVO(total, startPage, endPage, category,searchKeyword);
        }
        
		List<BoardVO> list = userService.getBoardList(vo);
		model.addAttribute("board",list);
		model.addAttribute("vo",vo);
		model.addAttribute("category",category);
		
		return "meeting/freeBoardList";
	}
	
	//봉사후기
	@GetMapping("reviewBoardList")
	public String reviewBoardList(PageVO vo, Model model,@RequestParam(name="volId") Integer volId,HttpServletRequest req
								, @RequestParam(value="searchKeyword", required = false)String searchKeyword
								, @RequestParam(value="category", required = false)String category
								, @RequestParam(value="start", required = false)String start
								, @RequestParam(value="end", required = false)String end) {
		int total = service.volActReviewListCnt(volId);
		int startPage = (start == null) ? 1 : Integer.parseInt(start);
		int endPage = (end == null) ? 10 : Integer.parseInt(end);
		
		if(searchKeyword == null) {
			vo = new PageVO(total, startPage, endPage, category);	            	
		}else {
			vo = new PageVO(total, startPage, endPage, category,searchKeyword);
		}
		
		List<VolActVO> list = service.volActReviewListPaging(vo);
		req.getSession().setAttribute("id",volId);
		model.addAttribute("board",list);
		model.addAttribute("vo",vo);
		model.addAttribute("category",category);
		
		return "meeting/reviewBoardList";
	}
	
	//자유게시판 작성폼
	@GetMapping("freeBoardInsertPage")
	public String freeBoardInsertPage(Principal prin,Model model) {
		if(prin != null && prin.getName() != null) {
	        model.addAttribute("userId",prin.getName());
	    } else {
	    	 model.addAttribute("userId","없음");
	    }
		return "meeting/freeBoardInsert";
	}
	
	@PostMapping("freeBoardInsert")
	@ResponseBody
	public int freeBoardInsert(BoardVO vo) {
		return service.insertBoard(vo);
	}
	
}
