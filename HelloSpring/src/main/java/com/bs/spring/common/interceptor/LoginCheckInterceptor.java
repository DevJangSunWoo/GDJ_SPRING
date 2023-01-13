package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bs.spring.member.vo.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

		// 로그인 여부를 체크하는 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=(HttpSession)request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있는 서비스 입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		
		return true;  //각 메서드의 반환값이 true이면 핸들러의 다음 체인이 실행되지만 false이면 중단하고 남은 인터셉터와  컨트롤러가 실행되지 않습니다.
		
	}

	
//	return 값이 true일 경우 정상적으로 진행이 되고,
//
//			false일 경우 실행 종료
//			Controller 진입 X
//
//			Parameter 중 Object handler는 HandlerMapping이 찾은 Controller Class 객체이다.
		
	
	
}
