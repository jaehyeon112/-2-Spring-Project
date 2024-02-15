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
import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActReviewVO;
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
   public String meetings(PageVO pvo,@RequestParam(name="volId") Integer volId,Model model,HttpServletRequest req,Principal prin,VolunteerVO volunteerVO) {
       if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
         model.addAttribute("userId","익명");
       }
       
      req.getSession().setAttribute("id",volId);
      VolunteerVO vo2 = service.meetingInfo(volId);
      model.addAttribute("info",vo2);
      
      pvo = new PageVO(10, 1, 10, volId,null);
      List<VolActVO> list = service.meetingVolActListPaging(pvo);
      model.addAttribute("act",list);
      
      List<VolMemVO> member = service.meetingMemList(volId);
      model.addAttribute("member",member);
      
      List<VolMemVO> cnt = service.volCnt(volId);
      model.addAttribute("cnt",cnt);
      
      
      Date today = new Date();
      model.addAttribute("today",today);
      
      List<VolActVO> review = service.volActReviewListPaging(pvo);
      for(VolActVO vo : review) {
         vo.setFilePath(service.findFile("p12",vo.getVolActId()));
      }
      model.addAttribute("review",review);
      
      List<VolActVO> after = new ArrayList<>();
      List<VolActVO> before = new ArrayList<>();
      for(VolActVO vo : list) {
         vo.setCnt(service.volActMemCnt(vo.getVolActId()));
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
   
//   @GetMapping("volActMemCnt")
//   @ResponseBody
//   public VolActVO volActMemCnt(@RequestParam(name="volActId") Integer volActId) {
//      return service.volActMemCnt(volActId);
//   }
   
   @PostMapping("approveMeeting")
   @ResponseBody
   public int approveMeeting(VolMemVO vo) {
      return service.approveMeeting(vo);
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
   public List<TagVO> meetingTag(TagVO vo) {
      return userService.tagList(vo);
   }
   
   //레이아웃에서 아작스로 받기
   @GetMapping("findMember")
   @ResponseBody
   public int findMember(@RequestParam(name="volId") Integer volId,@RequestParam(name="memId",required = false) String memId) {
      return service.findMember(volId,memId);
   }
   
   @GetMapping("meetingInfoPage")
   public String meetingInfo(@RequestParam(name="volId") Integer volId,Model model) {
      VolunteerVO vo = service.meetingInfo(volId);
      model.addAttribute("info",vo);
      return "meeting/meetingInfo";
   }
   
   //봉사게시판
   @GetMapping("volBoardList")
   public String volBoardList(Principal prin,PageVO pvo,@RequestParam(name="volId") Integer volId,Model model,HttpServletRequest req
                         , @RequestParam(value="start", required = false,defaultValue = "1")Integer start
                        , @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
      
      int total = service.meetingVolActListCnt(volId);

      pvo = new PageVO(total, start, end, volId,null);

        
      List<VolActVO> list = service.meetingVolActListPaging(pvo);
      req.getSession().setAttribute("id",volId);
      model.addAttribute("vo",pvo);
      model.addAttribute("act",list);
      Date today = new Date();
      
      for(VolActVO vo : list) {
         if(vo.getExpireDate().compareTo(today) < 0||vo.getVolDate().compareTo(today) < 0) {
            vo.setState(1);
         }else {
            vo.setState(0);
         }
      }
      
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           model.addAttribute("userId","없음");
       }
      
      return "meeting/volBoardList";
   }
   
   //봉사게시판 작성폼
   @GetMapping("insertVolActPage")
   public String insertVolActPage(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           model.addAttribute("userId","없음");
       }
      req.getSession().setAttribute("id",volId);
      
      return "meeting/volActBoardInsert";
   }
   
   @PostMapping("insertVolAct")
   @ResponseBody
   public int insertVolAct(VolActVO vo) {
      return service.insertVolAct(vo);
   }
   
   //봉사게시판 삭제
   @PostMapping("delVolActBoard")
   @ResponseBody
   public int delVolActBoard(Integer volActId) {
      return service.delVolActBoard(volActId);
   }
   
   @GetMapping("findVolActNo")
   @ResponseBody
   public int findVolActNo() {
      return service.findVolActNo();
   }
   
   //봉사게시판 정보
   @GetMapping("volActBoardInfo")
   public String volActBoardInfo(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req,@RequestParam Integer volActId) {
      VolActVO info = service.volActBoardInfo(volActId);
      model.addAttribute("info",info);
      req.getSession().setAttribute("id",volId);
      
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           model.addAttribute("userId","없음");
       }
      
      VolunteerVO vo = service.meetingInfo(volId);
      model.addAttribute("meeting",vo.getMemId());
      
      return "meeting/volActInfo";
   }
      
   //자유게시판
   @GetMapping("freeBoardList")
      public String freeBoardList(PageVO vo,FreeBoardVO freeVo, Model model,HttpServletRequest req,@RequestParam Integer volId,Principal prin
                           , @RequestParam(value="start", required = false,defaultValue = "1")Integer start
                           , @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
         int total = service.getBoardListCnt(volId);
           

           vo = new PageVO(total, start, end, volId,null);

           req.getSession().setAttribute("id",volId);
           
         List<FreeBoardVO> list = service.getBoardList(vo);
         model.addAttribute("board",list);
         model.addAttribute("vo",vo);
         System.out.println(list);
         
         if(prin != null && prin.getName() != null) {
              model.addAttribute("userId",prin.getName());
          } else {
              System.out.println("User is not logged in.");
          }
         
         return "meeting/freeBoardList";
      }
   
   //자유게시판 작성폼
   @GetMapping("freeBoardInsertPage")
   public String freeBoardInsertPage(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           model.addAttribute("userId","없음");
       }
      req.getSession().setAttribute("id",volId);
      
      return "meeting/freeBoardInsert";
   }
   //자유게시판 추가
   @PostMapping("freeBoardInsert")
   @ResponseBody
   public int freeBoardInsert(FreeBoardVO vo) {
      return service.insertBoard(vo);
   }
   
   //자유게시판 추가 번호
   @GetMapping("findNo")
   @ResponseBody
   public int findBoardNo() {
      return service.findBoardNo();
   }
   
   @GetMapping("findReviewNo")
   @ResponseBody
   public int findReviewNo() {
      return service.findReviewNo();
   }
   
   @GetMapping("updateFreeBoard")
   public String updateFreeBoard(FreeBoardVO freeVO,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      FreeBoardVO vo = service.freeBoardInfo(freeVO);
      model.addAttribute("vo",vo);
      return "meeting/updateFreeBoard";
   }
   
   @GetMapping("freeBoardInfo")
   public String freeBoardInfo(FreeBoardVO freeVO,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      FreeBoardVO vo = service.freeBoardInfo(freeVO);
      model.addAttribute("vo",vo);
      return "meeting/freeBoardInfo";
   }
   
   @PostMapping("updateFreeBoard")
   @ResponseBody
   public int updateFreeBoard(FreeBoardVO vo) {
      return service.updateFreeBoard(vo);
   }
   
   @PostMapping("deleteFreeBoard")
   @ResponseBody
   public int deleteFreeBoard(@RequestParam Integer volId,@RequestParam Integer boardNo) {
      return service.deleteFreeBoard(volId,boardNo);
   }
   
   
   //봉사후기
   @GetMapping("reviewBoardList")
   public String reviewBoardList(PageVO vo, Model model,@RequestParam Integer volId,HttpServletRequest req,Principal prin
                        , @RequestParam(value="start", required = false,defaultValue = "1")Integer start
                        , @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
      int total = service.volActReviewListCnt(volId);
      
      vo = new PageVO(total, start, end,volId,null);
      
      List<VolActVO> list = service.volActReviewListPaging(vo);
      req.getSession().setAttribute("id",volId);
      model.addAttribute("board",list);
      model.addAttribute("vo",vo);
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           System.out.println("User is not logged in.");
       }
      
      return "meeting/reviewBoardList";
   }
   
   //봉사후기 작성폼
   @GetMapping("reviewBoardInsertPage")
   public String reviewBoardInsertPage(VolActVO vo,Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
           model.addAttribute("userId","익명");
       }
      req.getSession().setAttribute("id",volId);
      
      
      VolActVO volVO = service.volActInfo(vo);
      model.addAttribute("volVO",volVO);
      return "meeting/reviewBoardInsert";
   }
   
   @PostMapping("reviewBoardInsert")
   @ResponseBody
   public int reviewBoardInsert(VolActReviewVO vo) {
      return service.insertReview(vo);
   }
   
   @GetMapping("ReviewInfo")
   public String ReviewInfo(Principal prin,Model model,VolActReviewVO vo,HttpServletRequest req,@RequestParam Integer reviewId,@RequestParam Integer volId) {
      if(prin != null && prin.getName() != null) {
           model.addAttribute("userId",prin.getName());
       } else {
          model.addAttribute("userId","익명");
       }
      
      vo.setReviewId(reviewId);
      req.getSession().setAttribute("id",volId);
      
      VolActReviewVO reviewInfo = service.ReviewInfo(vo);
      model.addAttribute("info",reviewInfo);
      return "meeting/reviewBoardInfo";
   }
   
   @PostMapping("delReview")
   @ResponseBody
   public int delReview(@RequestParam Integer reviewId) {
      return service.delReview(reviewId);
   }
}