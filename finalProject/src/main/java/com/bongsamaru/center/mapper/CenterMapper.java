package com.bongsamaru.center.mapper;

import java.util.List;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;

public interface CenterMapper {

	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCategoryCount(String category);
	public List<BoardVO> getNoticeList(PageVO vo);
	public List<BoardVO> getNoticeDetail(Integer boardId);
	public int getNoticeCount(PageVO vo);
}
