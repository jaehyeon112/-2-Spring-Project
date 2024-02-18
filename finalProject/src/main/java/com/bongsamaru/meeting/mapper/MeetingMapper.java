package com.bongsamaru.meeting.mapper;

import java.util.List;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActReviewVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;

public interface MeetingMapper {
	public VolunteerVO meetingInfo(Integer volId);
	public int withdrawalMeeting(VolMemVO vo);
	public List<VolMemVO> meetingMemList(VolMemVO vo);
	public List<VolMemVO> MemVolActList(Integer volId,String memId);
	public List<VolMemVO> volCnt(VolMemVO vo);
	public int volActMemCnt(Integer volActId);
	public int approveMeeting(VolMemVO vo);
	public List<VolActVO> volActReviewListPaging(PageVO pageVO);
	public int volActReviewListCnt(VolActReviewVO vo);
	public int findMember(Integer volId,String memId);
	public String findFile(String code,Integer codeNo);
	public int findBoardNo();
	public int findReviewNo();
	//자유게시판 관련
	public int insertBoard(FreeBoardVO vo);
	public int getBoardListCnt(Integer volId);
	public List<FreeBoardVO> getBoardList(PageVO pageVO);
	public FreeBoardVO freeBoardInfo(FreeBoardVO vo);
	public int updateFreeBoard(FreeBoardVO vo);
	public int deleteFreeBoard(Integer volId,Integer boardNo);
	
	public int delFile(String code,Integer codeNo);
	public VolActVO volActInfo(VolActVO vo);
	public int insertReview(VolActReviewVO vo);
	public VolActReviewVO ReviewInfo(VolActReviewVO vo);
	public int delReview(Integer reviewId);
	//봉사게시글 관련
	public int meetingVolActListCnt(Integer volId);
	public List<VolActVO> meetingVolActListPaging(PageVO pageVO);
	public VolActVO volActBoardInfo(Integer volActId);
	public int insertVolAct(VolActVO vo);
	public int delVolActBoard(Integer volActId);
	public int findVolActNo();
	
}
