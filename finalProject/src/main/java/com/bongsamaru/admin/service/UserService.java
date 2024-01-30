package com.bongsamaru.admin.service;

import java.util.List;

import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;

public interface UserService {
	public List<UserVO> getUserList(String memStat);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList();
}
