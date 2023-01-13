package com.bs.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	
	private MemoService service;
	
	@Autowired  ////public 으로 선언하기  //new 연산자 위임
	public MemoController(MemoService service) {
		this.service=service;
		
		
		
	}
	
	// get 방식으로 요청했을떄만  반응하겠다  내가    post방식으로 요청했을떄 응답하지 않겠다.
	@RequestMapping(value="/memo.do",method= {RequestMethod.GET})
	public ModelAndView selectmemoList(ModelAndView mv,
			@RequestParam( value="cPage",defaultValue="1")int cPage,
			@RequestParam( value="numPerpage",defaultValue="5")int numPerpage) {
		
		
		List<Memo> list=service.selectMemoListPage(
				Map.of("cPage",cPage,"numPerpage",numPerpage)
				);
		mv.addObject("memos",list);
		//log.debug("에러확인 :{}",list);
		
		//페이징 처리하기
		int totalData=service.selectMemoListCount();
		mv.addObject("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "memo.do"));
		
		
		
		mv.setViewName("memo/memolist");
		return mv;
		
	}
	
	
	
	@RequestMapping("/memoEnroll.do")
		public String memoEnroll() {
			
			return "memo/memoEnroll" ;
			
		}
	
	//method= {RequestMethod.POST}  사용시  사용하는 jsp  get 방식인지 post 방식인지 확인  설정안하면  default는 get 방식임
	//@RequestMapping(value="/memoEnrollEnd.do",method= {RequestMethod.POST})
	@PostMapping("/memoEnrollEnd.do")
	public ModelAndView insertMemo(Memo m,ModelAndView mv) {
		
		int result=service.insertMemo(m);
		
		
		String msg="";
		String loc="";
		
		
		if(result>0) {
			msg="메모 작성 축하드립니다.";
			loc="/memo/memo.do";
			
		}else {
			
			msg="메모작성 다시 해주오.";
			loc="/memo/memoEnroll.do";
			
		}
		
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		
		return mv;
		
		
		
	}
	
	
}
