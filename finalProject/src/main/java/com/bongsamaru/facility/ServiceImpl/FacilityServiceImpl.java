package com.bongsamaru.facility.ServiceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.mapper.FacilityMapper;
@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityMapper mapper;

	@Override
	public List<FacilityVO> getFacilityList(PageVO pageVO,String facZip2, String facType,@Param("facId") String facId) {
		return mapper.getFacilityList(pageVO,facZip2,facType,facId);
	}
	
	
	@Override
	public FacilityVO getFacilityInfo(String facId) {
		return mapper.getFacilityInfo(facId);
	}

	@Override
	public List<FundingVO> getFundingList(String facId) {
		return mapper.getfundingList(facId);
	}

	@Override
	public List<FundingVO> getFundedList(String facId) {
		return mapper.getfundedList(facId);
	}

	@Override
	public List<VolunteerVO> getVolunteerList(String facId) {
		
		return mapper.getVolList(facId);
	}

	@Override
	public List<DonaVO> getDonaList(String facId,Integer recStat) {
		return mapper.getDonaList(facId,recStat);
	}
	
	
	@Override
	public int InsertFacVol(VolActVO volActVO) {
		int result = mapper.InsertFacVol(volActVO);
		if(result ==1) {
			return volActVO.getVolActId();
		}else {
			return -1;
		}	
	}

	@Override
	public List<VolActVO> getVolunteerJoinList(String facId) {
		return mapper.getVolunteerJoinList(facId);
	}
	@Override
	public List<VolMemVO> getVolunteerAppList(Integer volActId) {
		return mapper.getVolunteerAppList(volActId);
	}
	
	//시설이 회원봉사 승인하면 업데이트
	@Override
	public int volAppUpdate(Integer volActId, String memId) {
		return mapper.volAppUpdate(volActId, memId);
	}
	//시설이 회원봉사 승인하면 인서트
	@Override
	public int volAppInsert(VolMemVO volMemVO) {
		return mapper.volAppInsert(volMemVO);
	}
	@Override
	public VolMemVO getJoinApp(Integer volActId) {
		return mapper.getJoinApp(volActId);
	}

	//페이지네이션하기 위한거
		@Override
		public int getCategoryCount(@Param("facZip2") String facZip2, @Param("facType") String facType) {
			
			return mapper.getCategoryCount(facZip2, facType);
		}


		
	

	
}


