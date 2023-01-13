package com.bs.spring.memo.model.vo;

import java.sql.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@Builder

@Data
public class Memo {
	private int memoNo;
	private String memo;
	private String password;
	private Date memoDate;
	
}
