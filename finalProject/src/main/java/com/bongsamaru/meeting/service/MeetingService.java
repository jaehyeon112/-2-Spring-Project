package com.bongsamaru.meeting.service;

import java.util.List;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface MeetingService {
	public VolunteerVO meetingInfo(Integer volId);
	public List<VolActVO> meetingVolActList(Integer volId);
	public int meetingVolActListCnt(Integer volId);
	public List<VolActVO> meetingVolActListPaging(PageVO pageVO);
	public List<VolMemVO> meetingMemList(Integer volId);
	public List<VolMemVO> volCnt(Integer volId);
	public VolActVO volActMemCnt(Integer volActId);
	public List<VolActVO> volActReviewListPaging(PageVO pageVO);
	public List<VolActVO> volActReviewList(Integer volId);
	public int volActReviewListCnt(Integer volId);
	public int findMember(Integer volId,String memId);
	public String findFile(String code,Integer codeNo);
	public int insertBoard(BoardVO boardVO);
}
