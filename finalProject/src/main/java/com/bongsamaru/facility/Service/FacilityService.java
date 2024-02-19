package com.bongsamaru.facility.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.mypage.service.HeartVO;

public interface FacilityService {

	public List<FacilityVO> getFacilityList(PageVO pageVO,@Param("facZip2") String facZip2, @Param("facType") String facType,@Param("facId") String facId); //시설페이지 리스트
	
	public FacilityVO getFacilityInfo(String facId);//시설상세페이지
	public List<FundingVO> getFundingList(String facId);//모금진행중 List
	public List<FundingVO> getFundedList(String facId);//모금마감 List
	public List<VolunteerVO> getVolunteerList(String facId);
	
	public int insertJoinVolunteer(VolMemVO volMemVO);//회원이 시설봉사 신청
	public VolActVO getFacVolInfo(Integer volActId);
	
	public List<DonaVO> getDonaList(String facId, Integer recStat);
	public int InsertFacVol(VolActVO volActVO);//시설이 봉사등록
	public List<VolMemVO> getVolunteerAppList(Integer volActId);//봉사참여하겠다고 신청한 참여자리스트
	public int volAppUpdate(Integer volActId, String memId);//참여 승인되서 업데이트
	public int volAppInsert(VolMemVO volMemVO);//참여 승인되서 인서트
	public VolMemVO getJoinApp(Integer volActId);//시설이 승인하려고 보는 신청서
	public int getJoinAppCheck(Integer volActId, String memId);//중복체크
	public int getFacRevCheck(Integer detailCate);//시설봉사후기 존재여부
	public List<VolActVO> getVolunteerJoinList(PageVO pageVO, String facId);
	public List<VolActVO> getVolunteerFinishList( PageVO pageVO, String facId); //봉사 완료 후 리스트
	public int getCategoryCount(@Param("facZip2") String facZip2, @Param("facType") String facType);
	
	public int getFVolCategoryCount(String facId);
	public int getFinishVolCategoryCount(String facId);
	//마음온도
	public int insertVolHeart(HeartVO heartVO);
	
	//봉사후기
	public void insertVolReview(BoardVO boardVO);
	public int updateVolReview(BoardVO boardVO);
	public BoardVO getVolReviewInfo(Integer detailCate);


}
