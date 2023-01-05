package com.bs.spring.demo.model.service;

import java.util.Map;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoUpdateService {
	
	Demo selectDemo(int no);
	
	int updateDemo(Demo d);
}
