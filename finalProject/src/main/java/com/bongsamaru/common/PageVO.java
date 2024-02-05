package com.bongsamaru.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageVO {
	
		private int nowPage; // 현재 페이지 
		private int startPage;
		private int endPage;
		private int total; // 전체페이지
		private int cntPerPage; // 한 페이지당 보여줄 갯수
		private int lastPage;
		private int start; 
		private int end;
		private int cntPage;
		private int PreviousPage = nowPage - 1; // 이전페이지
		private int NextPage = nowPage + 1; // 다음페이지
		private String category;
		
		public PageVO(int total, int nowPage, int cntPerPage, String category) {
			setNowPage(nowPage);
			setCntPerPage(cntPerPage);
			setTotal(total);
			calcLastPage(getTotal(), getCntPerPage());
			calcStartEndPage(getNowPage(), cntPage);
			calcStartEnd(getNowPage(), getCntPerPage());
			setPreviousPage(getNowPage() - 1);
	        setNextPage(getNowPage() + 1);
	        setCategory(category);
		}
		// 제일 마지막 페이지 계산
		public void calcLastPage(int total, int cntPerPage) {
			setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		}
		// 시작, 끝 페이지 계산
		public void calcStartEndPage(int nowPage, int cntPage) {
			setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
			if (getLastPage() < getEndPage()) {
				setEndPage(getLastPage());
			}
			setStartPage(getEndPage() - cntPage + 1);
			if (getStartPage() < 1) {
				setStartPage(1);
			}
		}
		// DB 쿼리에서 사용할 start, end값 계산
		public void calcStartEnd(int nowPage, int cntPerPage) {
			setEnd(nowPage * cntPerPage);
			setStart(getEnd() - cntPerPage + 1);
		}
}
