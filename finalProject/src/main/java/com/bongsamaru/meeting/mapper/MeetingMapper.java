package com.bongsamaru.meeting.mapper;

import java.util.List;

import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;

public interface MeetingMapper {
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
}
