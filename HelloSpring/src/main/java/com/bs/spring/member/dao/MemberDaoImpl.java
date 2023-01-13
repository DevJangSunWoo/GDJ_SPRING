package com.bs.spring.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Demo;
import com.bs.spring.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Override
	public void test() {
		System.out.println("dao 테스트");
	}
	
	
	@Override
	//Member m,SqlSessionTemplate session   순서 에러 발생    // 아 메소드 오버라이딩 현샹 떄문에
	public Member selectMemberById(SqlSessionTemplate session,Member m) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectMemberById",m);
	}
	
	@Override
	
	public int   memberEnrollEnd(SqlSessionTemplate session, Member m) {
		
		return session.insert("member.memberEnrollEnd",m);
	}


	@Override
	public List<Member> memberList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("member.memberList");
	}
	
	
	
	
}
