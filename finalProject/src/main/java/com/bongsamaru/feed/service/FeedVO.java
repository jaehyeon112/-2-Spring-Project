package com.bongsamaru.feed.service;

import java.util.Date;

import lombok.Data;

@Data
public class FeedVO {
	
	private Integer boardId;
	private String memId;
	private String memNick;
	private double tempChange;
	private String memProfile;
	private String title;
	private String filePath;
	private Integer likes;
	private Date createdAt;
	
}
