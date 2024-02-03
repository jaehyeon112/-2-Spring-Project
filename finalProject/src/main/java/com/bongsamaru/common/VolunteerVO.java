package com.bongsamaru.common;

import java.util.Date;

import lombok.Data;

@Data
public class VolunteerVO {
	private Integer vol;
	private Integer fac;
	private String memId;
	private Integer volId;
	private String meetName;
	private String meetType;
	private Integer cap;
	private String meetPurp;
	private String meetDesc;
	private String region;
	private Integer toomStat;
	private Integer meetingCnt;
	private Date recPeriod;
	private Date endPeriod;
}
