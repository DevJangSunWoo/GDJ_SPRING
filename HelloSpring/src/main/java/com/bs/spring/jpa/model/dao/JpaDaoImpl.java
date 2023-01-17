package com.bs.spring.jpa.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void insertMember(EntityManager em, JpaMember m) {
		// TODO Auto-generated method stub
		//매개변후로 전달받은 멤버 변수 저장하기
				em.persist(m);// 영속성 컨텍스트에 매개변수의 entityㄹ르 저장
		
		
	}

	@Override
	public JpaMember selectMemberById(EntityManager em, Long memberId) {
		
		
		return em.find(JpaMember.class, memberId);
	}

	@Override
	public void updateMember(EntityManager em, Map<String, Object> param, Long memberId) {
		// TODO Auto-generated method stub
		
		JpaMember m=em.find(JpaMember.class, memberId);
		m.setAge((Integer)param.get("age"));
		m.setHeight((Double)param.get("height"));
		m.setIntro((String)param.get("intro"));
		em.persist(m);
	}

	@Override
	public void deleteMember(EntityManager em, Long memberId) {
		// TODO Auto-generated method stub
		JpaMember m=em.find(JpaMember.class, memberId);
		em.remove(m);
		
		

	}

	@Override
	public  List<JpaMember> selectMemberAll(EntityManager em){
		
		// 전체조회하는 메소드를 제공하지않음.
				//JPQL구문을 이용해서 조회문을 작성해야한다.
				//JPQL은 java방식의 sql문 작성하는 방법 -> sql문 비슷함.
		//@Entity  명을 적거나  클래스 명을 명시
		return 	em.createQuery("select m from JpaMember m",JpaMember.class).getResultList();
		
	}
	
	
	// 페이징 처리는  sort of 등을 사용하여 할수 있다.
	@Override
	public List<JpaMember> selectMemberSearch(EntityManager em,Double height){
		
		return 	em.createQuery("select m from JpaMember m where height>=:param")
			.setParameter("param", height)
			.getResultList();
	}

	@Override
	public void insertMember(EntityManager em) {
		// TODO Auto-generated method stub
		JpaMember m1=JpaMember.builder().memberId("donghun").memberPwd("1234")
				.age(27).height(180.3).enrollDate(new Date()).intro("우리반 반장").build();
		
		JpaMember m2=JpaMember.builder().memberId("nari").memberPwd("333")
				.age(29).height(167.3).enrollDate(new Date()).intro("우리반 군기반장").build();
		
		
		Major major=Major.builder().majorName("코딩").professor("병승이").build();
		m1.setMajor(major);
		m2.setMajor(major);
	
		
		//entity 저장하기
		em.persist(m1);
		em.persist(m2);
	
	}
	
	
	
	
	
	
}
