package com.bongsamaru.user.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserFacilityVO;
import com.bongsamaru.common.VO.UserVO;

public interface UserMapper {
	public UserVO userList(String mem);
	public int countMemId(String MemId);
	public int countMemNick(String memNick);
	public int userSignUp(UserVO vo);
	public List<UserCategoryVO> categoryList();
	public int insertCate(@Param("id") String id, @Param("name") String name);
	public UserFacilityVO login(String id);
}
