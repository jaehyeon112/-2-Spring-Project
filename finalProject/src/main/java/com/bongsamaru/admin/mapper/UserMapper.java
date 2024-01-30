package com.bongsamaru.admin.mapper;


import java.util.List;

import com.bongsamaru.admin.service.UserVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;

public interface UserMapper {
	public List<UserVO> getUserList(String memStat);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList();
}
