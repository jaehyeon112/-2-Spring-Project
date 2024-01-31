package com.bongsamaru.common;

import java.util.Date;

import lombok.Data;

@Data
public class ReportVO {
	private Integer repId;
	private String category;
	private String memId;
	private Date repDate;
	private Date reportedDate;
	private String repReason;
	private String reqCode;
}
