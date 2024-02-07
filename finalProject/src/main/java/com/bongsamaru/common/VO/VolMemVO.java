package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VolMemVO {
	private Integer volMemId;
	private Integer volId;
	private String facId;
	private Integer appCode;
	private Date appDate;
	private String appReason;
	private String memId;
	private Integer volActId;
	private Integer cnt;
	private String path;
}
