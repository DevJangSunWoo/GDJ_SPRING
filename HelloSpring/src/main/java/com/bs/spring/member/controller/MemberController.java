package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
@Slf4j
// 이컨트롤러를 들어왔을떄    /member 라는  프리픽스가 붙음
public class MemberController {
	
	
	
	// 일반적으로 설정  또는  빈으로 등록해서 사용할수도 있음
	//private final Logger logger=LoggerFactory.getLogger(MemberController.class);

	
	
	//  @Autowired  타입으로 데이터를 집어넣는다.   빈들을 조회를 하겠지
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	private MemberController(MemberService service,BCryptPasswordEncoder passwordEncoder) {
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
	
	/*
	 * @RequestMapping("/test/") public void test() {
	 * System.out.println("controller-test() 실행"); service.test();
	 * 
	 * 
	 * // 다형성의 의해 상속받은 객체는 생성 가능 //MemberService s=new MemberService(); 인터페이스는 생성
	 * 불가능하나 //MemberService s=new MemberServiceImpl(); // 가능
	 * 
	 * }
	 */
		
		
		@RequestMapping("/loginMember.do")
		// public String login (String userId,String password)
		//               
	
		
//	public String login(Member m,HttpSession session)	{
		public String login(Member m,Model model)	{
			//Session에 데이터를 저장하고 관리
			Member loginMember=service.selectMemberById(m);
			
			//암호화된 패스워드를 원본값이랑 비교하기 위해서는 
			//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비교를 해야한다.
			//matches("원본값",암호화값)매소드를 이용
			if(loginMember!=null&& 
					//loginMember.getPassword().equals(m.getPassword())) {
					passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
				//로그인성공
			//	session.setAttribute("loginMember", loginMember);
				model.addAttribute("loginMember",loginMember);
				
			}
			
			return "redirect:/";
			
		}
		
		
		@RequestMapping("/memberLogout.do")
	//	public String loginOut(HttpSession session){
			
		public String loginOut(SessionStatus session){
			
		
		//세션을 제거한느 메소드
			//session.invalidate();
			
			if(!session.isComplete()) {   //세션확인   //세션이 쇼멸되지 않았니
 				session.setComplete();// session을 삭제
				
			}
			
			
			
			//메인으로 가라는 뜻
			return "redirect:/";
		}
		
		
		
		@RequestMapping("/memberEnroll.do")
		public String memberEnroll() {
			
			
			
			return "/member/memberEnroll";
		}
		
		
		
			@RequestMapping("/memberEnrollEnd.do")
			public  ModelAndView  memberEnrollEnd(ModelAndView mv,Member m) {
			log.debug("파라미터로 전달된 member : {}",m);
				
				//password 암호화 처리하기
			String encodePassword=passwordEncoder.encode(m.getPassword());
			m.setPassword(encodePassword);
			
			
				
				int result=service.memberEnrollEnd(m);
				
				String msg="";
				String loc="";
				
				
				if(result>0) {
					msg="회원가입 성공 축하드립니다.";
					loc="/";
					
				}else {
					
					msg="회원가입 실패.";
					loc="/member/memberEnroll.do";
					
				}
				
				
				mv.addObject("msg",msg);
				mv.addObject("loc",loc);
				mv.setViewName("common/msg");
				
				return mv;
			}
		
			
			//상세페이지로 이동
			@RequestMapping("/memberView.do")
			public String memberView(Member m,Model model) {
				
			
				
				Member  viewMember=service.selectMemberById(m);
					
				model.addAttribute("member",viewMember);
				
				
				return "/member/memberView";
			}
			
			
		
		
}
