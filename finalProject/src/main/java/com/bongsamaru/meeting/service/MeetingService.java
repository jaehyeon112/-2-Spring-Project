package com.bongsamaru.meeting.service;

import java.util.List;

import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface MeetingService {
	public VolunteerVO meetingInfo(Integer volId);
	public List<VolActVO> meetingVolActList(Integer volId);
	public List<VolMemVO> meetingMemList(Integer volId);
	public List<VolMemVO> volCnt(Integer volId);
	public VolActVO volActMemCnt(Integer volActId);
}
