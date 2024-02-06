package com.bongsamaru.center.service;

import java.util.List;

import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

public interface CenterService {
	
	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCategoryCount(String category);
	public int getFaqCount();
}
