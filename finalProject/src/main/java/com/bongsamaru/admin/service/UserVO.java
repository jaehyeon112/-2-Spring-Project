package com.bongsamaru.admin.service;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String memId;
	private String memPwd;
	private String memNick;
	private String memPhone;
	private Integer memZip;
	private String memAddr;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memBdate;
	private String memDept;
	private String memEmail;
	private String donReceipt;
	private String memSsn;
	private String emailAgree;
	private String smsAgree;
	private String acctNum;
	private String bankName;
	private String memStat;
	private String memName;
}
