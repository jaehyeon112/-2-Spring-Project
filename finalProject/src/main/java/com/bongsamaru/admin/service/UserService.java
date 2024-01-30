package com.bongsamaru.admin.service;

import java.util.List;

import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.DonationLedgerVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;

public interface UserService {
	public List<UserVO> getUserList(String memStat);
	public List<BoardVO> getBoardList(String category);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList();
	public int insertNotice(BoardVO boardVO);
	public List<FaqVO> getFaqList();
	public UserVO getUserOne(String memId);
	public VolunteerVO volCount(String memId,String mId);
	public DonationLedgerVO donCount(String memId);
	public FacilityVO getFacilityInfo(String facId);
}
