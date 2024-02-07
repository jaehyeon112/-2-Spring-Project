package com.bongsamaru.meeting.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
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

}
