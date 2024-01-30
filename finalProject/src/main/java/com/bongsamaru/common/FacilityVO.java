package com.bongsamaru.common;

import java.util.Date;

import lombok.Data;
@Data
public class FacilityVO {
	
	private String facId;
	private String bizNum;
	private String facPwd;
	private String facName;
	private String facType;
	private Number facZip;
	private String facAddr;
	private String facAddrDetail;
	private String resion;
	private Date facEstDate;
	private String facEmail;
	private String facWeb;
	private String facIntro;
	private String repName;
	private String repPhone;
	private String facBank;
	private String donAcct;
	private String donApp;
	private String memApp;
}