package com.bongsamaru.meeting.mapper;

import java.util.List;

import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;

public interface MeetingMapper {
	public VolunteerVO meetingInfo(Integer volId);
	public List<VolActVO> meetingVolActList(Integer volId);
	public List<VolMemVO> meetingMemList(Integer volId);
	public List<VolMemVO> volCnt(Integer volId);
	public VolActVO volActMemCnt(Integer volActId);
}
