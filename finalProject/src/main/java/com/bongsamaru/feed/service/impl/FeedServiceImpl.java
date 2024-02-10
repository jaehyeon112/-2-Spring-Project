package com.bongsamaru.feed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
