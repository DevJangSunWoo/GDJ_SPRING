package com.bs.spring.jpa.model.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;
import com.bs.spring.jpa.model.entity.JpaMember;

@Service
public class JpaServiceImpl implements JpaService {

	
	
	private EntityManager em; //?
	private JpaDao dao;
	
	
	
	@Autowired
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		super();
		this.em = em;
		this.dao = dao;
	}

	@Override
	public void insertMember(JpaMember m) {
		// EntityManager 가 트렌젝션을 시작하고 db와 연동해서 처리
		// EntityManager.getTransaction() 메소드를 이용해서  트렌젝션 생성
		 EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		 dao.insertMember(em, m);
		 et.commit(); // 트렌젝션 저장  및 완료! ->flush() 메소드가 실행되면서 sql  문실행
	}

	@Override
	public JpaMember selectMemberById(Long memberId) {
		EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		 JpaMember m= dao.selectMemberById(em, memberId);
		 et.commit();
		// TODO Auto-generated method stub
		return m;
	}

	@Override
	public void updateMember(Map<String, Object> param, Long memberId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMember(Long memberId) {
		// TODO Auto-generated method stub

	}

}
