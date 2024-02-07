package com.bongsamaru.meeting.service;

import java.util.List;

import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface MeetingService {
	public VolunteerVO meetingInfo(Integer volId);
	public List<VolActVO> meetingVolActList(Integer volId);
}
