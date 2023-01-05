package com.bs.spring.demo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.vo.Demo;

@Service
public class DemoServiceImpl implements DemoService {

	private DemoDao dao;
	private SqlSessionTemplate session;
	
	//빈으로 등록 된것을 받을떄는   @Autowired    DemoDao dao,SqlSessionTemplate session  타입을 보고 집어 넣준다.
	@Autowired
	public DemoServiceImpl(DemoDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public int insertDemo(Demo demo) {	
		return dao.insertDemo(session, demo);
	}
	@Override
	public List<Demo> selectDemoList(){
		return dao.selectDemoList(session);
	}
	
	
	
}
