package com.bongsamaru.meeting.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.PageVO;
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
	public List<VolActVO> meetingVolActList(Integer volId) {
		return mapper.meetingVolActList(volId);
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
	public VolActVO volActMemCnt(Integer volActId) {
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
	public List<VolActVO> volActReviewList(Integer volId) {
		return mapper.volActReviewList(volId);
	}

	@Override
	public String findFile(String code,Integer codeNo) {
		return mapper.findFile(code,codeNo);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return mapper.insertBoard(boardVO);
	}

	@Override
	public int findBoardNo() {
		return mapper.findBoardNo();
	}

	@Override
	public BoardVO freeBoardInfo(Integer detailCate) {
		return mapper.freeBoardInfo(detailCate);
	}

	@Override
	public int updateFreeBoard(BoardVO vo) {
		return mapper.updateFreeBoard(vo);
	}

	@Transactional
	@Override
	public int deleteFreeBoard(Integer detailCate) {
		mapper.delFile("p13",detailCate);
		return mapper.deleteFreeBoard(detailCate);
	}


}
