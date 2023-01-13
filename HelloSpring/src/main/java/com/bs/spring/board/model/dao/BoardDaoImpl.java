package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	
	
	//전체
	@Override
	public List<Board> selectBoardListPage(SqlSessionTemplate session,Map<String,Integer>param){
		RowBounds rb=new RowBounds((param.get("cPage")-1)*param.get("numPerpage"),param.get("numPerpage"));
		return session.selectList("board.selectBoardList", null, rb);
		
	}
	//페이징
	@Override
	public int selectBoardListCount(SqlSessionTemplate session) {
		
		return session.selectOne("board.selectBoardListCount");
		
	}
	
	
	
	//삽입등록
	@Override
	public 	int insertBoard(SqlSessionTemplate session,Board b) {
		
		return session.insert("board.insertBoard",b);
	}
	
	
	
	
	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment attachment) {
		// TODO Auto-generated method stub
		return session.insert("board.insertAttachment",attachment);
	}
	
	
	//상세
	@Override	
	public 	Board selectBoard(SqlSessionTemplate session,int boardNo) {
		
		return session.selectOne("board.selectBoard",boardNo);
	}
	
	
	
	
	//조회수시도
	
	@Override
	public int selectBoardCount(SqlSessionTemplate session,int no) {
		
		return	session.selectOne("board.selectBoardCount",no);
	
	}
		
		
	@Override
	public	Board searchBoardNo(SqlSessionTemplate session,Map param) {
		
		return session.selectOne("board.searchBoardNo",param);
	}
	
	@Override
	
	public	int updateReadcount(SqlSessionTemplate session, int no) {
		
		return session.update("board.updateReadcount", no);
	}
	
	
	
}
