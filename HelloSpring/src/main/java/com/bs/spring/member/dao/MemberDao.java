package com.bs.spring.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.vo.Demo;
import com.bs.spring.member.vo.Member;

public interface MemberDao {
	void test();
	
	 Member selectMemberById(SqlSessionTemplate session,Member m);
	 
	  int memberEnrollEnd(SqlSessionTemplate session, Member m);
	 
}
