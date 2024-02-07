package com.bongsamaru.meeting.mapper;

import java.util.List;

import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface MeetingMapper {
	public VolunteerVO meetingInfo(Integer volId);
	public List<VolActVO> meetingVolActList(Integer volId);
}
