package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VolunteerVO {
	private String volActId; 
	private String revTitle;
	private String content; 
	private Date writeDate; 
	private String facId; 
	private String category; 
	private String title; 
	private String location; 
	private Date volDate;
	private Integer cap;
	private String facName; 
	//영희
	private Integer vol;
	private Integer capCnt;
	private Integer fac;
	private String memId;
	private Integer volId;
	private String meetName;
	private String meetType;
	//private Integer cap;
	private String meetPurp;
	private String meetDesc;
	private String region;
	private String oneLiner;
	private Integer roomStat;
	private Integer meetingCnt;
	private Date recPeriod;
	private Date endPeriod;
	private Date appDate;
}
