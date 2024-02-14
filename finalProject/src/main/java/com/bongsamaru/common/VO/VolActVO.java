package com.bongsamaru.common.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date volDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date expireDate;
	private String path;
}
