package com.bs.spring.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
//public class LoggerInterceptor  implements HandlerInterceptor{
	public class LoggerInterceptor  extends HandlerInterceptorAdapter{
//		private final Logger logger=LoggerFactory.getLogger(LoggerInterceptor.class);
	
//	spring 5.3 version 이상에서는 HandlerInterceptorAdapter 를 사용하는 대신 HandlerInterceptor를 implements 해서 사용하는 방식으로 바뀌었다고 한다.
//	(필자는 springboot 2.3.x to 2.5.x 로 변경하니 spring version 이 5.3 이상으로 올라가서 이렇게 되었다.)

//인터셉터 의 사용예시    Login Session 검증, Header 검증, Token 검증	
//인터셉터의 활용으로 얻을수 있는 장점
//	공통 코드 사용으로 코드 재사용성 증가
//	메모리 낭비, 서버 부하 감소
//	코드 누락에 대한 위험성 감소	
	
		// 상황에 따른 실행할 메소드르 재정의	
		// 트루면 뒤에 메소드 실행   펄스면  실행하지 않음
		@Override
		public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object handler) throws Exception{
			
		log.debug("전처리 인터셉터 실행");
		log.debug("메소드 실행전");
		log.debug("리퀘스트 url 값: "+req.getRequestURI());
		Map param=req.getParameterMap();   //  getParameterMap() 의  반화값이 스트링 배열이다.  String[]
		for(Object o :param.keySet()) {
			log.debug("{}",o+": "+param.get(o));  //  param.get(o) 배열이다
		}
		log.debug("-----");	
			
		
		
		
		//응답메세지 작선하기
//			res.setContentType("text/html;charset=utf-8");
//			res.getWriter().append("<h2>interceptor가 응답함</h2>");
			
			//Object handler 이용
			HandlerMethod method=(HandlerMethod)handler;
			// 실행되는 클래스 확인하기
			log.debug("{}",method.getBean());
			//((DemoController)method.getBean());
			// 실행되는 메소드 확인하기
			log.debug("{}",method.getMethod());
		
			//false 일떄는 흰색창 띔
			// 반환값이 true controller 메소드를 실행
			// 반환값이 false 명 controller 메소드를 실행하지 않음
			return true;
		}
		
		// 매핑 메소드의 실행이 끝난뒤에 실행되는 메소드
		// 주로 화면을 처리하기전에 동작 
		@Override
		public void postHandle(HttpServletRequest req,HttpServletResponse res,
				Object handler, ModelAndView mv)throws Exception {
			log.debug("----후처리 인터셉터실행");
			log.debug("요청주소 {}",req.getRequestURI());
			log.debug("응답화면 : {}",mv.getViewName());
			log.debug("전송데이터 : {}",mv.getModelMap());
			log.debug("-----");
			// 중단시키고 싶다면
			//throw new IllegalAccessError("잘못된 접근입니다.");
			
			
		}

		
		//alt shift s v
		//DispatcherServelt 의  화면처리가 완료된후
		// 아주문이  완료된후에 못들어가게 막아야하나?
		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
			// TODO Auto-generated method stub
			log.debug("----응답후  인터셉터실행");
			log.debug("요청주소 {}",request.getRequestURI());
			log.debug("에러메세지 : {}",ex!=null?ex.getMessage():"성공");
			//예외처리가 있다 즉 에러가 발생했다면 에러 메세지를 표출해라   에러가 발생하지 않으면 성공 출력 !!!
			log.debug("-----");
		}
		
		
		
		
}
