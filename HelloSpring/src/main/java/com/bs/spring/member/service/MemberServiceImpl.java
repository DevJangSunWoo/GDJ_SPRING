package com.bs.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	
	@Override
	public void test() {
		System.out.println("\"서비스 테스트 실행\"");
		dao.test();
		
		
	}
}
