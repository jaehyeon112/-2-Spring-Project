package com.bongsamaru.center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.center.mapper.CenterMapper;
import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

@Service
public class CenterServiceImpl implements CenterService{
	
	@Autowired
	CenterMapper centerMapper;
	
    @Override
    public List<FaqVO> getFaqList(PageVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getFaqList(vo);
    }
    
    @Override
    public int getFaqCount() {
    	// TODO Auto-generated method stub
    	return centerMapper.getFaqCount();
    }
    
    @Override
    public int getFaqCategoryCount(String category) {
    	// TODO Auto-generated method stub
    	return centerMapper.getFaqCategoryCount(category);
    }
    
    @Override
    public List<BoardVO> getNoticeList(BoardVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getNoticeList(vo);
    }
}
