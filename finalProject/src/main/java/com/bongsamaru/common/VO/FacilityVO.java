package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;


@Data
public class FacilityVO {
	
	private String facId;
	private String bizNum;
	private String facPwd;
	private String facName;
	private String facType;
	private Integer facZip;
	private String facAddr;
	private String facAddrDetail;
	private String region;
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
	private String facZip2;  //지역(우편번호 앞 2)
	
	//join문으로 생긴 별칭
	
	 private Integer donaitonAmt; 
	 private Integer donors;
	 
	 
}
