package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VolActVO {
	private Integer volActId;
	private Integer volId;
	private String facId;
	private String category;
	private String title;
	private String content;
	private String location;
	private Integer cap;
	private Integer cnt;
	private Date volDate;
}
