package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class AlertVO {
	private Integer alterId;
	private String category;
	private String content;
	private Date alertDate;
	private String readFlag;
	private String memId;
}