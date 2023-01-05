package com.bs.spring.demo.model.vo;



import java.sql.Date;  

//import java.util.Date;   객체 생성하지 못함

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Demo {
	private int devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;
	private Date birthday;  
}
