package com.bs.spring.jpa.model.dao;

import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.model.entity.JpaMember;

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

	}

	@Override
	public void deleteMember(EntityManager em, Long memberId) {
		// TODO Auto-generated method stub

	}

}
