package com.bs.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Demo;

@Repository
public class DemoDaoImpl implements DemoDao {

	@Override
	public int insertDemo(SqlSessionTemplate session, Demo demo) {
		// TODO Auto-generated method stub
		return session.insert("demo.insertDemo",demo);
	}

	@Override
	public List<Demo> selectDemoList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("demo.selectList");
	}

}
