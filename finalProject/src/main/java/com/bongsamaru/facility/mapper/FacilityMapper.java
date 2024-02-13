package com.bongsamaru.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList(@Param("facZip2") String facZip2, @Param("facType") String facType);
	public FacilityVO getFacilityInfo(String facId);
	public List<FundingVO> getfundingList(String facId);
	public List<FundingVO> getfundedList(String facId);
	public List<VolunteerVO> getVolList(String facId);
	
	//시설 마이페이지(기부)
	public List<DonaVO> getDonaList(String facId);
	
	//시설 마이페이지(봉사)
	public  int InsertFacVol(VolActVO volActVO);//시설이 봉사 신청하기위한 등록폼
	//public VolunteerVO insertJoinVolunteer();//회원이 시설 봉사 참가하기위한 등록폼
	public List<VolActVO> getVolunteerJoinList(String facId); //신청 수락 기다리는 리스트
	//public List<VolunteerVO> getVolunteerFinishList(); //봉사 완료 후 리스트
	//public VolunteerVO volunteerFinishInfo(); //봉사 완료 된 후 참여자 승인해주기 위해
	
}
