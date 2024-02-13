package com.bongsamaru.feed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.InterestVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.feed.mapper.FeedMapper;
import com.bongsamaru.feed.service.FeedService;
import com.bongsamaru.feed.service.FeedVO;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	FeedMapper feedmapper;
	
	@Override
	public List<FeedVO> getFeedList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getFeedList(memId);
	}
	
	@Override
	public List<FeedVO> getFeedFirstList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getFeedFirstList(memId);
	}
	
	@Override
	public List<InterestVO> getInterestList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getInterestList(memId);
	}
	
	@Override
	public int LikeInsert(String memId, Integer boardId) {
		// TODO Auto-generated method stub
		return feedmapper.LikeInsert(memId, boardId);
	}
	
	@Override
	public boolean getLike(String memId, Integer boardId) {
		LikeVO vo = new LikeVO();
		vo.setMemId(memId);
		vo.setBoardId(boardId);
	    LikeVO likeStatus = feedmapper.getLike(vo);
	    
	    return likeStatus != null ? true : false;
	}
	
	@Override
	public int DeleteLike(Integer boardId) {
		// TODO Auto-generated method stub
		return feedmapper.DeleteLike(boardId);
	}
}
