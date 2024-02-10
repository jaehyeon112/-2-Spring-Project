package com.bongsamaru.feed.mapper;

import java.util.List;

import com.bongsamaru.feed.service.FeedVO;


public interface FeedMapper {
	
	// 메인페이지 전체리스트
	List<FeedVO> getFeedList(String memId);
		
}


