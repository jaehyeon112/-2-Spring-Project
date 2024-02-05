package com.bongsamaru.common;

import lombok.Data;

@Data
public class FaqVO {
	private Integer faqId;
	private String category;
	private String title;
	private String content;
	private boolean expanded;
}
