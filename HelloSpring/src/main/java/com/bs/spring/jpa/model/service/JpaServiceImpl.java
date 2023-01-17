package com.bs.spring.jpa.model.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;
import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;

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
		EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		
		 dao.updateMember(em,param, memberId);
		 et.commit();
		
		
	}

	@Override
	public void deleteMember(Long memberId) {
		// TODO Auto-generated method stub
		EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		
		 dao.deleteMember(em, memberId);
		 et.commit();
		
		
	}

	
	@Override
	public List<JpaMember> selectMemberAll() {
		return dao.selectMemberAll(em);
	}
	@Override
	public	 List<JpaMember>  selectMemberSearch(Double height){
		
		return dao.selectMemberSearch(em,height);
	}

	@Override
	public void insertMember() {
		// TODO Auto-generated method stub
		
		EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		
		 dao.insertMember(em);
		
		 et.commit();
	}

	@Override
	public Major selectMajor(Long no) {
		// TODO Auto-generated method stub
		return dao.selectMajor(em,no);
	}

	@Override
	public void insertStudentClub() {
		// TODO Auto-generated method stub
		EntityTransaction et= em.getTransaction();
		 et.begin(); // 트렌젝션 실행!
		
		 dao.insertStudentClub(em);
		
		 et.commit();
		
		
	}
	

	@Override
	public Student selectStudent(Long no) {
		
		return  dao.selectStudent(em, no);
	}

	@Override
	public Club selectClub(Long no) {
		// TODO Auto-generated method stub
		return  dao.selectClub(em, no);
	}
	
	
	
	
	
	
}
