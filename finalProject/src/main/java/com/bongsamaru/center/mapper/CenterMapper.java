package com.bongsamaru.center.mapper;

import java.util.List;

import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

public interface CenterMapper {

	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCount();
}
