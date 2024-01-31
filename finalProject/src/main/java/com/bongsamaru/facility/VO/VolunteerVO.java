package com.bongsamaru.facility.VO;

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
}
