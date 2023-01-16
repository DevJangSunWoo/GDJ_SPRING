package com.bs.spring.jpa.model.entity;

import java.util.Date; //자바 꺼지 떄문에 util 로 임포트하기

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
//생성한 클래스를 jpa와 연동하는 Entity로 등록하려면 어노테이션을 이용한다.
//@Enttity어노테이션 -> jpa 관리한느 db와 연동되는 객체를 의마함.


//자바 코드로 되있는 테이블이라고 생각하기
// jpa 관리한다는 것은  db랑 연결되는 클래스를 관리한다는 것
@Data
@Entity
public class JpaMember {

	@Id// 컬럼으로 생성할때 pk 값을 설정한것
	private long memberNo;
	
	private String memberId;
	private String memberPwd;
	private int age;
	private double height;
	private Date enrollDate;
}
