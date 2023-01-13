package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

//Aspect로 등록하여 멤버 메소드를 특정시점에
//실행시키기!!!
@Slf4j
public class LoggerAspect {
	
	//aspect클래스에서 메소드를 선언할떄는 지정 advisor 따라 메소드 선언방식이 달라짐
	//타켓 메소드가 실행되지전에 실행하는 메소드 구현하기  -> BEFORE 
	public void loggerBefore(JoinPoint jp) {// 전이나 후애 실행할 메소드선언
		log.debug("loggerAspect실행함");
		//JoinPoint 객체 aop설정에 의해 메소드가 실행될떄 정보를 확인할 수 있음.
		// 넘머가는 매개변수를 확인가능
		// 메소드 실행전 실행후의    정보
		//타켓클래스와 메소드 확인하기
		
		//  Signature 인터페이스를 이요해서
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" : "+sig.getName());   // 클래스 DeclaringType   
		log.debug("==========");
		
	}

	public void loggerAfter(JoinPoint jp) {
		
		log.debug("loggerAfter 메소드 실행");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" : "+sig.getName());   // 클래스 DeclaringType   
		log.debug("==========");
		
		
	}
	
	
}
