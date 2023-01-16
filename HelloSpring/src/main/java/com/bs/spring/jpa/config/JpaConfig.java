package com.bs.spring.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
	//jpa로db와 클래스를 연겨하기 위해서는
	//jpa가 제공하는 entityManager ,entityTransaction 클래스를 이용
	//Persistence 클래스에서 제공하는 createEntityManagerFactory 메소드를 이용해서
	//EntityManagerFactory 클래스를 생성
	//EntityMangerFActory 클랫의 createEntityManager()메소드 이용해서 EntityManager 클래스를 생성
	
	// 생성해야핳ㄹ  객체들을 bean으로 등록하기
	@Bean 
	public EntityManagerFactory  entityManagerFactory() {
		//persistence.xml 에 설정한 persistence-unit name 값으로 연결할
		//db를 설정해서 생성함.
		//createEntityManagerFactory  메소드의 인자로 대입해줍니다.
		return Persistence.createEntityManagerFactory("bstest");
	}
	
	
	@Bean
	public EntityManager entityManager() {
		
		return entityManagerFactory().createEntityManager();
		
	}
	
}
