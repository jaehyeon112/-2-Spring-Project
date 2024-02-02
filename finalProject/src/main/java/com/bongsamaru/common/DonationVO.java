package com.bongsamaru.common;

import java.util.Date;

import lombok.Data;

@Data
public class DonationVO {
	private String donId;
	private String facId;
	private String title;
	private Date recPeriod;
	private Date endPeriod;
	private Date extPeriod;
	private Integer goalAmt;
	private String projTarget;
	private String intro;
	private String recStat;
	private String donRegApp;
	private String expEffect;
}
