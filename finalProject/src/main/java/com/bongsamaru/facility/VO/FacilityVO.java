package com.bongsamaru.facility.VO;

import java.util.Date;

import lombok.Data;
@Data
public class FacilityVO {
	
	private String facId;
	private String 사업자등록번호;
	private String facPwd;
	private String facName;
	private String facType;
	private String facZip;
	private String facAddress;
	private String facDetailAddress;
	private String addSel;
	private Date fac설립년월일;
	private String facEmail;
	private String facHomePage;
	private String facIntroduce;
	private String 대표자명;
	private String 대표전화;
	private String 은행명;
	private Integer 후원금계좌번호;
	private String 기부승인여부;
	private String 가입승인여부;
}
