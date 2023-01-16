package com.bs.spring.jpa.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.MemberLevel;
import com.bs.spring.jpa.model.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JpaContorller {
		
	private JpaService service;

	// alt ahift s o
	@Autowired
	public JpaContorller(JpaService service) {
		super();
		this.service = service;
	}
		
	
	@RequestMapping("/jap/insert")
	public String insertMember() {
		JpaMember m=JpaMember.builder().memberId("user01").memberPwd("1234").memberLevel(MemberLevel.GOLD)
				.age(19).height(180.5).enrollDate(new Date())
				.intro("하하하  벌써 1월이 지났네").build();
		service.insertMember(m);
		return "redirect:/";
	}
	
	
	@RequestMapping("/jpa/member")
	public String selectMemberById(long id) {
		service.selectMemberById(id);
		JpaMember m=service.selectMemberById(id);
		
		log.debug("{}",m);
		
		return "redirect:/";
	}
	
	
}
