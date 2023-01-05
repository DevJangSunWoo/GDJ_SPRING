package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.service.MemberService;
//import com.bs.spring.member.service.MemberServiceimpl;
import com.bs.spring.member.service.MemberServiceImpl;

@Controller
public class MemberController {
	//  @Autowired  타입으로 데이터를 집어넣는다.   빈들을 조회를 하겠지
	@Autowired
	private MemberService service;
		
		@RequestMapping("/test/")
		public void test() {
			System.out.println("controller-test() 실행");
			service.test();
			
			
			// 다형성의 의해  상속받은 객체는    생성 가능
			//MemberService s=new MemberService(); 인터페이스는 생성 불가능하나
			//MemberService s=new MemberServiceImpl();  //   가능
			
		}
}
