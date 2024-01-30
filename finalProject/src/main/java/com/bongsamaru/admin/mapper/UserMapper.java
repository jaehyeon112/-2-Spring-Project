package com.bongsamaru.admin.mapper;


import java.util.List;

import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;

public interface UserMapper {
	public List<UserVO> getUserList(String memStat);
	public List<FaqVO> getFaqList();
	public List<BoardVO> getBoardList(String category);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList();
	public UserVO getUserOne(String memId);
	public int insertNotice(BoardVO boardVO);
	public VolunteerVO volCount(String memId);
}
