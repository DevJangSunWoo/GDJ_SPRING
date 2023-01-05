package com.bs.spring.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
	private String name;
	private int age;
	 private String gender;
	 private double weight;
	 
	 //특정한 필드값을 파라미터로 받는  생성자를 선언
	 public Animal(String name,int age) {
		 this.name=name;
		 this.age=age;
	 }
	 
	 public void initTest() {
		 
		 System.out.println("생성후 실해항는ㄴ 매소드");
	 }
	 
	 
	 public void destroyTest() {
		 
		 System.out.println("객체 소멸  실해항는ㄴ 매소드");
	 }
	 
	 
}
