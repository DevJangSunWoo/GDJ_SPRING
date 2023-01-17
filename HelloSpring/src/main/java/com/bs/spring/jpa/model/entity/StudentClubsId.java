package com.bs.spring.jpa.model.entity;

import java.io.Serializable;

import lombok.Data;

// 복합키 식별자 클래스로
//복합키 식별자 클래스는 조건이 있음
//1.기본 생성자가 있어야한다.
//2. 클래스가 public 으로 선언되어야 한다.
//3. Serializable 인터페이스를 구현해야한다.
//4. equals,hashcode 메소드가 오버라이딩 되있아야한다.

@Data
public class StudentClubsId  implements Serializable{
	//studnet ,club을 복합키로 연결하는 클래스
	private long student; //StudentClub 클래스의 Student 클래스 필드명
	private long club; // /StudentClub 클래스의 Club 클래스의 필드명
	
}
// 똑같은 스튜턴드에