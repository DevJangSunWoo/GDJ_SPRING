package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;

@Component
@Aspect
public class AdminCheckAspect {
	// 관리자 일대일 방등에  사용 가능
	@Before("execution(* com.bs.spring.memo..select*(..))")
	public void adminCheck(JoinPoint jp) {
		//로그인 정보 가져오기
		//RequestContextHolder 클래스에서 currentRequestAttribute()static 메소드 제공
		//이 메소드를 이용하면 session객체를 가져올 수 있다.
		HttpSession session=(HttpSession)RequestContextHolder.currentRequestAttributes()
				.resolveReference(RequestAttributes.REFERENCE_SESSION);
		
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null||!loginMember.getUserId().equals("admin")) {
			throw new AdminAccessException("관리자만 접근 가능!:(");
//			try {
//			throw new IllegalAccessException("관리자만 접근 가능!:(");
//			}catch (IllegalAccessException e) {
//				throw new RuntimeException(e.getMessage());
//				
//			}
		
		}
		
	}
	
	
	
}
