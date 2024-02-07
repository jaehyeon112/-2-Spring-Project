package com.bongsamaru.center.service;

import java.util.List;

import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

public interface CenterService {
	
	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCategoryCount(String category);
	public List<BoardVO> getNoticeList(PageVO vo);
	public int getNoticeCount(PageVO vo);
}
