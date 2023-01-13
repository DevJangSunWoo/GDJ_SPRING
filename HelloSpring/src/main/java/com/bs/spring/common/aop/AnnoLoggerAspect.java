package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;
//어노테이션으로 aop 적용하기
@Component   //
@Aspect    // aop에서 사용하는 클래스다.
@Slf4j
public class AnnoLoggerAspect {
	//aspect클래스 안에는  공통으로 적용되는 메소드가 있어야함
	// pointcut  ,메소드  , advisor시점
	
	//pointcut 등록하기 // 메소드 위에 선언가능
	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {}
	
	//advisor 등록
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug(sig.getName()+"실행전");
		log.debug(sig.getDeclaringTypeName());
		log.debug("파라미터 데이터 받아오기");
		Object[]params=jp.getArgs();
		if(params!=null) {
			for(Object o : params) {
				log.debug("파라미터 : {}",o);
			}
		}
		
		log.debug("========");
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug(sig.getName()+"실행후");
		log.debug(sig.getDeclaringTypeName());
		log.debug("========");
		
	
	}
	
	
	// 실행 전후 모두 실행하는 메소드 구현하기
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")
	public Object demoLoggerAround(ProceedingJoinPoint join) throws Throwable{
		//Object obj=join.proceed();     후
		//실행전, 후를 구분하는 메소드-> join.proceed();
		StopWatch stop=new StopWatch();
		stop.start();
		Object[] params=join.getArgs();
		Signature sig=join.getSignature();
		
		Object obj=join.proceed();   // 기준
		stop.stop();
		log.debug("실행시간 :"+stop.getTotalTimeMillis()+"ms");
		
		return obj;	
		//return	join.proceed();  // 전 코드
	}
	
	// 단일로 쓰고 버릴거면
	@AfterThrowing("execution(* com.bs.spring..*(..))")
	public void exceptionTest(JoinPoint jp) {
		log.debug("에러 발생 !!!!");
		
	}
	
}
