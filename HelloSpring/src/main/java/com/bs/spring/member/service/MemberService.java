package com.bs.spring.member.service;

import com.bs.spring.demo.model.vo.Demo;
import com.bs.spring.member.vo.Member;

public interface MemberService {
	
	void test();
	
	 
	 
	 Member selectMemberById(Member m);
	 
	 
	 
	  int  memberEnrollEnd(Member m);
	
}
