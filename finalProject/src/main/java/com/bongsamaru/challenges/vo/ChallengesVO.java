package com.bongsamaru.challenges.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChallengesVO {
	private  Integer chalId;
	  private String memId;
	  private String title;
	  private String content;
	  private String ExpImpact;
	  private String ActionReason;
	  private String RegDate;
	  private String PartMethod1;
	  private String PartMethod2;
	  
	  private Integer chalDetId;
	  private String dContent;
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date  partDate;
	  
	  
	  private int fileId;
	  private String filePath;
	  private int fileOrder;
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date uploadDate;
	  private String fileName;
	  private String extension;
	  private int fileSize;
	  private String code;
	  private String codeNo;
}
