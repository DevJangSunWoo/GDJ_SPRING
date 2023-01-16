package com.bs.spring.jpa.model.entity;

import java.util.Date; //자바 꺼기 떄문에 util 로 임포트하기

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
//생성한 클래스를 jpa와 연동하는 Entity로 등록하려면 어노테이션을 이용한다.
//@Enttity어노테이션 -> jpa 관리한느 db와 연동되는 객체를 의마함.


//자바 코드로 되있는 테이블이라고 생각하기
// jpa 관리한다는 것은  db랑 연결되는 클래스를 관리한다는 것
@Data
@Entity //(name="jpaMember")   기본적으로  클래스 이름을 가져다씀
@Table(name="jpa_member")// db테이블을 설정   schema,catalog 속성 
//설정 uniqueConstraints  컬럼에 대한  unique   제약조건 설정 (테이블 레벨에서...)
@SequenceGenerator(name="seq_jpamemberno",sequenceName = "seq_jpamemberno",initialValue = 1)// sequence 를 생성하는 어노테이션
public class JpaMember {

	@Id// 컬럼으로 생성할때 pk 값을 설정한것
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_jpamemberno") // 자동생성된 값을 대입해주는 설정  * 오라클에서 sequence 이용
	@Column(name = "member_no")// 컬럼설정  //  name  컬럼명 바꾸기
	private long memberNo;
	@Column(name = "member_id" ,nullable = false,unique = true,length=80)  // not null 제약조건   unique 제약 조건  // 길이
	private String memberId;
	@Column(name = "member_pwd" ,nullable = false,length=80)
	private String memberPwd;
	private int age;
	private double height;
	
	@Column
	@Enumerated(EnumType.STRING)//enumtype 에 대한 설정
	private MemberLevel memberLevel;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
	@Lob  // 4000바이트 넘어가면  에러남   @Lob으로 선언하면 자동으로 CLOB으로 선언됨
	private String intro;
	
//	@Lob// BLOB으로 처리됨
//	private byte[] data;
	
}
