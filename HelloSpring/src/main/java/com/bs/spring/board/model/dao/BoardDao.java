package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

public interface BoardDao {

	int insertBoard(SqlSessionTemplate session,Board b);  //작성

	int insertAttachment(SqlSessionTemplate session,Attachment attachment);
	
	
	Board selectBoard(SqlSessionTemplate session,int boardNo);  //상세
	
	
	
	List<Board> selectBoardListPage(SqlSessionTemplate session,Map<String,Integer>param);  //전체
	
	int selectBoardListCount(SqlSessionTemplate session);  //페이징
	
	
	
	//조회수시도
	int selectBoardCount(SqlSessionTemplate session,int no);
		
		
	
	Board searchBoardNo(SqlSessionTemplate session,Map param);
	
	int updateReadcount(SqlSessionTemplate session, int no);
	
	
}
