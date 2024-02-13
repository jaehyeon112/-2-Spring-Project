package com.bongsamaru.feed.service;

import java.util.List;

import com.bongsamaru.common.VO.InterestVO;

public interface FeedService {

	public List<FeedVO> getFeedList(String memId);
	public List<FeedVO> getFeedFirstList(String memId);
	public List<InterestVO> getInterestList(String memId);
	public int LikeInsert(String memId, Integer boardId);
	public boolean getLike(String memId, Integer boardId);
	public int DeleteLike(Integer boardId);
}
