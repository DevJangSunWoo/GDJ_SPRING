package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;
import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"loginMember"})
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	//	등록되어있는 springbean 은 필드선언해서 사용
	//@Autowired   처음 기준은 타입임    만약 빈에 동일한 타입이 두개 지정되면 에러가 나옴
	// 헤결방법  필드명을  id값과 일치하게 넣어주면됨.

	// Error creating bean with name 'homeController': Unsatisfied dependency expressed through field 'a'; nested except  를 위한 주석 처리
//	@Autowired
//	@Qualifier(value="alonge")
//	private Animal a ;   //  
//	
//	
//	@Autowired
//	@Qualifier(value="dog")
//	private Animal b;
//	
//	
//	@Autowired
//	@Qualifier(value="getDongmin")
//	private Person p;
	
	
	//required=false    객체가없으면 null 출력해라 의미임
	// 선언안했을시 기본적으로   required  true 임
	@Autowired(required=false)
	private Food food;
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IllegalAccessException{
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if(1==1) throw new IllegalAccessException("내맘대로 에러!!");
		
		return "home";
	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		
		Cookie c= new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		
		//new 연산자 아에 못쓰는것은 아님
		//등록된 springbean  출력하기
		
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");
		
//		System.out.println(p);
//		System.out.println(food);
		
//		System.out.println(alonge);
//		System.out.println("dog:"+dog);
		
		//메인화면으로 출력해주는 mapping 메소드
		//WEB-INF-//views/return값.jsp  -> request.getRequestDispatcher("WEB-INF/views/return값.jsp").forward(req,res);
		
		
		//1월 9일  
		//Logger 가 제공하는 메소드 이용해서 log 출력하기
		//메소드 :debug ,info,warn,error 
		//메소드는 출력되는 상황에 따라 결정해서 사용
		//debug : 개발시에 사용하는 로그    // 변수명을 사용한다거나  그 흐름에서 움직이는 지확인하는ㄴ
		//info : 프로그램 실해중 사용자에게 전달해야 하는 메세지 로그
		//warn : 프로그램이 멈추지 않으나  비정상적으로 로직이 돌아갔을떄 경고 로그 // 사용자가 이상하게 이용할떄 경고하는 
		//error : 에러가 났을떄 ! 로그 
		
		//logger 태그에  설정되어 있는 level에 따라 메소드 실행여부가 결정됨
		//debug<info<warn<error
		//  내가 디버그 결종했으면  디버그부터   에러까지 메소드 실행   만약  내가  info 설정했다  ㅑinfo 부터  에러까지
  		
//		logger.debug("난 debug야");
//		logger.info("난 info");
//		logger.warn("난 warn 이랴");
//		logger.error("난 error이야");
		
		//logger로 다른 타입의 값 출력하기
		// 왼쪾 매개변수에 패턴 설정
		// 오른 쪽 매개변수는 오브젝트
		//logger.debug("foor {}",food);
		
		return "index";
	}
	
	@RequestMapping("/error.do")
	public String loginFail() {
		//인증실패 후 실행되는 메소드
		throw new   AdminAccessException("로그인 실패");
		 
		
	}
	
	
	
	@RequestMapping("/successLogin.do")
	public String successLogin(Model m) {
		//인증 후 실행되는 메소드
		Object o=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("{}",o);
		
		
	//	m.addAttribute("loginMember",Member.builder().userId(((User)o).getUsername()).build());
		//이제는 userdetail 을 이용하여 구현했으니  이제는 user로 형변화 하면 안됨
		m.addAttribute("loginMember",(Member)o);
		
		
		
		return "redirect:/";
	}
	
	
	
}
