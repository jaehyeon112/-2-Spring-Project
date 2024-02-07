package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class ReportVO {
	private Integer repId;
	private String category;	//신고 카테고리
	private Integer categoryNo;		//신고 상세카테고리
	private String memId;
	private Date repDate;
	private String repReason;
	private Integer reqCode;	//신고 처리상태
}
