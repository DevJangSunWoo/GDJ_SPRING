package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	
	
	public DemoController(DemoService service) {
		
		this.service=service;
	}
	
	
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한  서비스 주소(URL) 에 맞는 메소드르 구현
	//메소드구현 할떄 서비스주소와 연결해주는 어노테이션을 선언
	//@RequestMapping(연결주소[,추가 옵션설정]) ,
	// -Rest 방식의 서비스 구현시 사용 @GetMapping @PostMapping  ... 어노테이션 사용
	
	//demo 페이지는 연결하는 메소드 구현
	//반환형을 String -> SpringBean으로 등록된  ViewResolver를  통해 페이지를 지정된 위치에서 찾아 응답
	//매개변수는 없음
	//메소드명은 알아서 하시오!!
	//스프링에서 매핑 핸들러가 서비스는 알아서  선택함
	@RequestMapping("/demo/demo.do")
	public String  demoPage() {
		//InternalResourceViewResolver  가 처리함
		//InternalResourceViewResolver  에 저장된 prefix, suffix 의 내용을 반환값이랑 연결해서 view 화면을 찾음
		//prefix : /WEB-INF/views
		//suffix : .jsp
		//  /WEB-INF/views/views/demo/demo.jsp -> RequestDispatcher.forward() 를 실행함.
		// request.getRequestDispatcher(" /WEB-INF/views/demo/demo.jsp").forward(request,response);
		return "demo/demo";
	}
	
	//요청매칭 메소드의 매개변수와 반환형
	//1.반환형
	//-String : ViewResolver에 의해서 view 화면을 반환할떄 사용
	//-void  : HttpResponse로 직접 응답메세지를 작성할떄 사용 (upload,download..)
	//-ModelAndView  :ModelAndView 객체를 반환(화면정보,view에 전송할 데이터를 가지고 있음)
	//-클래스타입(RefferenceType)  : 생성된 객체를 반환 -> json 으로 반환   // jackson 라이브러리 사용
	
	//2.매개변수 ->  Spring이 알아서 넣어줌!
	//-HttpServletRequest : 서블릿의 그녀석 !!
	//-HttpServletResponse : 서블릿의 그 녀석 !!
	//-HttpSession : 서블릿의 그녀석
	//-java.util.Locale : 운영된고 있는 서버의 지역정보
	//- InputStream/Reader : 파일 IO할떄 사용하는 stream객체
	//-OutputStream/Writer : 파일 IO할떄  사용하는 sream 객체
	//-기본자료형 변수 : 클라이언트가 보낸 name 명칭과 변수명이 일치하면 대입해줌.   // request.getParameter 사용안해도됨
	//	-name과 일치하지 않은 변명을 사용했을떄는 @RequestParam 어노테이션을 이용해서 mapping  할 수 있음.
	//	-추가적인 설정이 필요할떄도 @RequestParam 어노테이션을 사용할 수 있음.
	// 	-* 주의 기본자료형 변수를 선언했을떈느 반드시 모든 변수에 연결되는 값을 전달해야힘.   //  @RequestParam  없이  매핑할 값이 없으면 에러가 남
	//-클래스(RefferenceType) 변수 : command 라고 하고 클라이언트가 보낸 
	//						데이터를 지정한 타입의 클래스를 생성해서 대입해줌
	//		- 필드명과 클라이언트가 보낸 name명이 일치하는 값
	//-java.util.Map : 클라이언트가 보낸 데이터를 map으로 대입해줌.
	//-Model : 서버에서 데이터를 저장하는 용도의 객체   1회용 데이터 저장  // 생명주기가 request와 동일  새로운 주소가 들어오면  소멸
	//ModelAndView : 저장한 데이터,화면 정보를 한번에 저장하는 객체 1회용	
	
	
	//-기본자료형변수 선언시 @RequestHeader,@CookieValue 어노테이션을 이용하면
	//header 나  cookie 값을 저장할 수 있음
	
	//추가 어노테이션
	//@ResponseBody -> json 형태로 반환할때 사용
	//@RequestBody -> json 형태로 전달된  데이터를 vo객체와 mapping 해주는 것
	
	
	//서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
	//public String demo1(HttpServletRequest req,HttpServletResponse res) {
		public void demo1(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
		System.out.println(req);
		System.out.println(res);
		
		//res.setCharacterEncoding("utf-8");
		String name=req.getParameter("devName");
		int age=Integer.parseInt(req.getParameter("devAge"));
		String gender=req.getParameter("devGender");
		String email=req.getParameter("devEmail");
		String [] devlang=req.getParameterValues("devLang");
		System.out.println(name+age+gender+email);
		for(String d : devlang) {
			System.out.println(d);
			
		}
		
		Demo d=Demo.builder().devName(name).devAge(age).devEmail(email).devGender(gender).devLang(devlang).build();
		
		req.setAttribute("demo", d);
		
		//방식2
		// 이구문 실행시  예외처리 해줘야함
		req.getRequestDispatcher("/WEB-INF/views/demo/demoresult.jsp")
		.forward(req, res);
		
		//방식3
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out=res.getWriter(); 
//		out.print("<h1>데이터</h1>");
		
		//방식 1
		//return "demo/demoresult";
	}
	
	//기본 자료형으로 데이터 받아오기
	// 만약에  값 미입력시  400에러발생
	// 프론트에   required  처리하면 상관없음
	
	@RequestMapping("/demo/demo2.do")
	public String basicType(String devName,int devAge,String devEmail,
			String devGender,String[] devLang,Model model) {
		
		System.out.println(devName+devAge+devEmail+devGender);
		
		for(String l: devLang) {
			
			System.out.println(l);
		}
		
		
		//model은 저장용도
		//Model model 은   request에 비해  들어있는 데이터들이  상대적으로 적음
		Demo d=Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		
		//model 객체에 데이터 저장하기
		//key ,value 형식으로 데이터를 저장함
		//addAttribute() 메소드를 이용
		model.addAttribute("demo",d);
		
		
		
		return "demo/demoresult";
	}
	
	//키값이 맞지 않을떄
	//defaultValue="1"   속성값이니   "" 무조건 해줘야함
	// 나이   defaultValue  사용하고    //required=false  값이 없을시 null 나오게 한가. //
	//jsp 화면과   내부 로직에   null 아닐시  분기 처리를 해줘야함
	@RequestMapping("/demo/demo3.do")
	public String requestParamTest(
			@RequestParam(value="devName")	String name,
			@RequestParam(value="devAge",defaultValue="1")int age,
			@RequestParam(value="devEmail")String email,
			@RequestParam(value="devGender")String gender,
			@RequestParam(value="devLang",required=false)String[] lang,
			Model model) {
		
		
		System.out.println(name+age+gender+email);
		if(lang!=null) {
		for(String d : lang) {
			System.out.println(d);
			
		}
		
		}
		
		Demo d=Demo.builder().devName(name).devAge(age).devEmail(email).devGender(gender).devLang(lang).build();

		
		
		
		model.addAttribute("demo",d);
		
		return "demo/demoresult";
	}
	
	
	// 조건은   객체필드명과     파라미터값이 일치하는 값만
	//객체기 떄문에  배열을 포함한 일반자료형만 받을 수 있음
	//Date 같이 오브젝트는 받지 못함
	//import java.util.Date;  불가능
	//import java.sql.Date;    가능
	
	@RequestMapping("/demo/demo4.do")
	public String commandMappingTest(Demo demo,Model model) {
		System.out.println(demo);
		
		model.addAttribute("demo",demo);
		
		return "demo/demoresult";
	}
	
	
	
	
	//jsp 상에서  여러가지를 선택해도  인덱스 0번  하나만 가져오
	// 그래서  Map 으로 가져올떄는  단일값일때만 가져오면됨
	// 그러나 표현하는 방법이 있음
	//	매개변수   String[] devLang    ,      param.put("devLang", devLang)   put메소드를 사용하여  넣을수도 있음.
	//required 처리와  분기처리도 생각해야함
	@RequestMapping("/demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param,String[] devLang,Model model) {
		System.out.println(param);
		param.put("devLang", devLang);
		
		model.addAttribute("demo",param);
		
		return "demo/demoresult";
	}
	
	
	//쿠기값이 있을수도 없을수도 있으니  required=false 처리 하기
	//편의성을 위해서 사용하느것 
	@RequestMapping("/demo/demo6.do")
	public String extraTest(@CookieValue(value="testData",required=false) String testData,
			@RequestHeader(value="User-agent")String useragnet,
			@SessionAttribute(value="sessionId") String id,
			@RequestHeader(value="Referer")  String referer
			) {
		
		System.out.println(testData);
		System.out.println(useragnet);
		System.out.println(id);
		System.out.println(referer); //  required=false 처리 하기
		
		
		
		return "demo/demoresult";
	}
	
	//ModelAnd View  로 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewTest(ModelAndView mv,Demo demo) {
		//ModelAndView 객체는 view 설정과, 데이터 저장을 같이 할 수 있는 객체
		//data 저장 : addObject("key",value) 메소드 이용
		//view  설정 : setView("view이름")  메소드 이용
		
		mv.addObject("demo",demo);
		mv.setViewName("demo/demoresult");
		
		return mv;
	}
	
	//restful 하게 서비스를 구현할떄 사용  -> @RestController를 사용하게됨
	// rest 방식은  데이터만 던져주는      화면구현은 프론트에서       
	//CSR은 Client Side Rendering의 약자로 서버로부터 전달받은 데이터를 클라이언트 측에서 직접 렌더링 하는 방식을 의미한다.
//	@GetMapping   조회할떄
//	@PostMapping    저장할때
//	@PutMapping    수정할떄
//	@DeleteMapping   삭제할떄
//	@PathVariable   // 주소 값을 받아올떄
	
	
	//org.springframework.http.converter.HttpMessageNotWritableException: 
	//No converter found for return value of type: class java.util.ImmutableCollections$ListN
	// 중간에 제이슨으로 반환해주는 라이브러리가 필요함
	//-클래스타입(RefferenceType)  : 생성된 객체를 반환 -> json 으로 반환   // jackson 라이브러리 사용
	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	public List<String> responseTest() {
		
		return   List.of("1","2","3","4");
	}
	
	
	@RequestMapping("demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		
		int result=service.insertDemo(demo);
		//spring 에서   redirect처리하기
		//redirect  예약어 사용
		return "redirect:/demo/demo.do";
	}
	
	
	@RequestMapping("demo/demolist.do")
	public ModelAndView demoList(ModelAndView mv) {
		
		List<Demo> list=service.selectDemoList();
		mv.addObject("demos",list);
		mv.setViewName("demo/demolist");
		
		for (int i = 0; i < list.size(); i++) {
			   System.out.println(list.get(i));
			  }
			
		
		
		return mv;
		
		
		
	}
	
	
}
