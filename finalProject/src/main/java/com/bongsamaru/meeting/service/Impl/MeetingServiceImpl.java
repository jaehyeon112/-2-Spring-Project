package com.bongsamaru.meeting.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActReviewVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.meeting.mapper.MeetingMapper;
import com.bongsamaru.meeting.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService{

	@Autowired
	MeetingMapper mapper;
	
	@Override
	public VolunteerVO meetingInfo(Integer volId) {
		return mapper.meetingInfo(volId);
	}

	@Override
	public List<VolMemVO> meetingMemList(Integer volId) {
		return mapper.meetingMemList(volId);
	}
	
	@Override
	public List<VolMemVO> volCnt(Integer volId) {
		return mapper.volCnt(volId);
	}

	@Override
	public int volActMemCnt(Integer volActId) {
		return mapper.volActMemCnt(volActId);
	}

	@Override
	public List<VolActVO> meetingVolActListPaging(PageVO pageVO) {
		return mapper.meetingVolActListPaging(pageVO);
	}

	@Override
	public int meetingVolActListCnt(Integer volId) {
		return mapper.meetingVolActListCnt(volId);
	}

	@Override
	public List<VolActVO> volActReviewListPaging(PageVO pageVO) {
		return mapper.volActReviewListPaging(pageVO);
	}

	@Override
	public int volActReviewListCnt(Integer volId) {
		return mapper.volActReviewListCnt(volId);
	}

	@Override
	public int findMember(Integer volId, String memId) {
		return mapper.findMember(volId, memId);
	}

	@Override
	public String findFile(String code,Integer codeNo) {
		return mapper.findFile(code,codeNo);
	}

	@Override
	public int insertBoard(FreeBoardVO vo) {
		return mapper.insertBoard(vo);
	}

	@Override
	public int findBoardNo() {
		return mapper.findBoardNo();
	}

	@Override
	public FreeBoardVO freeBoardInfo(FreeBoardVO vo) {
		return mapper.freeBoardInfo(vo);
	}

	@Override
	public int updateFreeBoard(FreeBoardVO vo) {
		return mapper.updateFreeBoard(vo);
	}

	@Transactional
	@Override
	public int deleteFreeBoard(Integer volId,Integer boardNo) {
		mapper.delFile("p13",boardNo);
		return mapper.deleteFreeBoard(volId,boardNo);
	}

	@Override
	public List<VolMemVO> MemVolActList(Integer volId, String memId) {
		return mapper.MemVolActList(volId, memId);
	}

	@Override
	public VolActVO volActInfo(VolActVO vo) {
		return mapper.volActInfo(vo);
	}

	@Override
	public int findReviewNo() {
		return mapper.findReviewNo();
	}

	@Override
	public int insertReview(VolActReviewVO vo) {
		return mapper.insertReview(vo);
	}

	@Override
	public VolActReviewVO ReviewInfo(VolActReviewVO vo) {
		return mapper.ReviewInfo(vo);
	}

	@Override
	public int delReview(Integer reviewId) {
		return mapper.delReview(reviewId);
	}

	@Override
	public VolActVO volActBoardInfo(Integer volActId) {
		return mapper.volActBoardInfo(volActId);
	}

	@Override
	public int insertVolAct(VolActVO vo) {
		return mapper.insertVolAct(vo);
	}

	@Override
	public int delVolActBoard(Integer volActId) {
		return mapper.delVolActBoard(volActId);
	}

	@Override
	public int findVolActNo() {
		return mapper.findVolActNo();
	}

	@Override
	public List<FreeBoardVO> getBoardList(PageVO pageVO) {
		return mapper.getBoardList(pageVO);
	}

	@Override
	public int getBoardListCnt(Integer volId) {
		return mapper.getBoardListCnt(volId);
	}

	@Override
	public int approveMeeting(VolMemVO vo) {
		return mapper.approveMeeting(vo);
	}

	@Override
	public int withdrawalMeeting(VolMemVO vo) {
		return mapper.withdrawalMeeting(vo);
	}


}
