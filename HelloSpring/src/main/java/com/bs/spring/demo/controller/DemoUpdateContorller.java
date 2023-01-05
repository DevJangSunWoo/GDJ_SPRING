package com.bs.spring.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.demo.model.service.DemoUpdateService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoUpdateContorller {

	
	private DemoUpdateService service;
	
	@Autowired
	public DemoUpdateContorller(DemoUpdateService service) {
		
		this.service=service;
		
		
	}
	
	
	
	//페이지 이동
	@RequestMapping("demo/demoUpdate.do")
	public String  demoUpdate(int no,Model m) {
		
		Demo d=service.selectDemo(no);
		m.addAttribute("demo",d);
		return "demo/demoupdate";
	}


	@RequestMapping("demo/demoupdateEnd.do")
	public String updateDemo(Demo demo) {
			
		//System.out.println("수정테스트전"+demo);
		
		int result=service.updateDemo(demo);
		
	//	System.out.println("수정테스트후"+demo);
		
		return  "redirect:/demo/demolist.do";
	}
 	
	
	
	
	
	
	
}
