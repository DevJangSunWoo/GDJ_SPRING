package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.vo.Board;

public interface BoardService {

	int insertBoard(Board b);  // 작성
	
	List<Board> selectBoardListPage(Map<String,Integer>param);  // 전체리스트
	int selectBoardListCount();  //페이징
	
	Board selectBoard(int boardNo); // 상세화면
	
	
	//조회수시도
	int selectBoardCount(int no);
	Board searchBoardNo(Map param,boolean readFlag);
	
	
	
}
