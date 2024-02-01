package com.bongsamaru.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DonledgerVO {
	
	private Integer donLedId;
	private Integer donId;
	private Integer donAmt;
	private Integer payMethod;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDate;
	private String payStat;
	private Integer payId;
	private String anonCheck;
	private String memId;
}
