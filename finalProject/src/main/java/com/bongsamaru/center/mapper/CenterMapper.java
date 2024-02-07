package com.bongsamaru.center.mapper;

import java.util.List;

import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

public interface CenterMapper {

	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCategoryCount(String category);
	public List<BoardVO> getNoticeList(PageVO vo);
	public int getNoticeCount(PageVO vo);
}
