package com.bs.spring.jpa.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;
import com.bs.spring.jpa.model.entity.StudentClubs;

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
		//major  엔티티이기 떄문에  둘다 영속성contex에  올려야함
		//major도  올려야함  먼저
		
		em.persist(major);
		em.persist(m1);
		em.persist(m2);
	
		//major  엔티티이기 떄문에  둘다 영속성contex에  올려야함
		//major도  올려야함  먼저
		
	}

	@Override
	public Major selectMajor(EntityManager em, Long no) {
		// TODO Auto-generated method stub
		return em.find(Major.class, no);
	}

	@Override
	public void insertStudentClub(EntityManager em) {
		// TODO Auto-generated method stub
		
		Student s=Student.builder().studentName("김유준").grade(3).classNumber(1).build();
		Student s1=Student.builder().studentName("이동민").grade(2).classNumber(2).build();
		Student s2=Student.builder().studentName("임연지").grade(3).classNumber(3).build();
		Student s3=Student.builder().studentName("이병도").grade(3).classNumber(3).build();
		Student s4=Student.builder().studentName("큐티장").grade(2).classNumber(2).build();
		
		
		
		Club c=Club.builder().name("불량").location("체육관").build();
		Club c1=Club.builder().name("등산").location("뒷산").build();
		Club c2=Club.builder().name("코딩").location("정보화 교육실").build();
		
		
//		s.setClubs(List.of(c1,c2));
//		s2.setClubs(List.of(c2));
//		s1.setClubs(List.of(c1,c2,c));
//		s3.setClubs(List.of(c));
//		s4.setClubs(List.of(c));
		
		
		//#
//		StudentClubs sc= StudentClubs.builder().student(s).club(c).enrollDate(new Date()).build();
//		StudentClubs sc1= StudentClubs.builder().student(s1).club(c1).enrollDate(new Date()).build();
//		StudentClubs sc2= StudentClubs.builder().student(s).club(c2).enrollDate(new Date()).build();
//		StudentClubs sc3= StudentClubs.builder().student(s1).club(c2).enrollDate(new Date()).build();
		
		em.persist(c);
		em.persist(c1);
		em.persist(c2);
		
		em.persist(s);
		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.persist(s4);
		StudentClubs sc= StudentClubs.builder().student(s).club(c).enrollDate(new Date()).build();

		
		//#
		em.persist(sc);
//		em.persist(sc1);
//		em.persist(sc2);
//		em.persist(sc3);
		
		
		
		
	}
	
	
	@Override
	public Student selectStudent(EntityManager em,Long no) {
		
		return em.find(Student.class, no);
	}

	@Override
	public Club selectClub(EntityManager em, Long no) {
		// TODO Auto-generated method stub
		return em.find(Club.class, no);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
