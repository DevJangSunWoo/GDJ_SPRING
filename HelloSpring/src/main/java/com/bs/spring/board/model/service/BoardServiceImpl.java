package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	private SqlSessionTemplate session;
	
	
	
	
	@Autowired
	public  BoardServiceImpl(BoardDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
		
	}
	
	//리스트
	@Override
	public List<Board> selectBoardListPage(Map<String,Integer>param){
		
		return dao.selectBoardListPage(session,param);
		
	}
	
	//페이징
	@Override
	public  int selectBoardListCount() {
		
		return dao.selectBoardListCount(session);
	}
	
	
	// 삽입     
	@Override
	//@Transactional	    // 익센셥 처리시  이제  rollback 처리해줌
	public  int insertBoard(Board b){
		//1.게시글을 등록하고
		//2.첨부파일을 등록
	//	int boardNo=0;   // 마이바티스 기능으로 필요 x
		log.debug(" 인서트 전"+b.getBoardNo());	  //  0이 나오지  
		int result= dao.insertBoard(session,b);         // 세션이 종료되면  커밋 처리됨
		
		// int result= dao.insertBoard(session,b);  이 구문이 끝난 시점에서  시퀀스 생김
		
		// if(result>0) boardNo=dao.selectBoardSeq(session);   // 마이바티스 기능으로 필요 x
		 //b.setBoardNo(boardNo);   // 마이바티스 기능으로 필요 x
		log.debug(" 인서트 후"+b.getBoardNo());	  // 이제  해당하는 시퀀스 값이 나옴
		if(result>0) {
			result=0;	
			for(Attachment a : b.getFiles()) {
						 a.setBoard(b);  
						 result+=dao.insertAttachment(session,a);  //  수정시 += 로 누적이됨    일반등록시  =  result+=dao.insertAttachment(session,a);
				 
				 }
			if(result!=b.getFiles().size()) {
				       // 1 !=  2    즉 하나만 업데이트된 경우는  실패뛰우는것임
					// 이경우는 0행이 발생되는 경우가 아니니까  
					//  하기처럼  임의로   runtimeException()을 발생시켜서    등록실패하게 만듬
				throw new RuntimeException();
			}
			
			
		} else {
			
			throw new RuntimeException("입력실패!");
		}
		return result;
	}
	
	
	
	
	//상세
	@Override
	public  Board selectBoard(int boardNo){
		
		return dao.selectBoard(session,boardNo);
	}
	
	
	
	
	
	
	//조회수시도
	@Override
	public int selectBoardCount(int no) {
		
		return dao.selectBoardCount(session,no);
	}
	
	
	@Override
	
	public Board searchBoardNo(Map param,boolean readFlag) {
		
		Board board=dao.searchBoardNo(session,param);
		
		if(board != null && !readFlag) {
			//조회수 증가시켜주기 !
			int result = dao.updateReadcount(session, (int)param.get("no"));
			if(result>0) {
				session.commit();
				board.setBoardReadCount(board.getBoardReadCount() + 1);
			}
			else session.rollback();
		}
		session.close();
		
		
		
		
		return dao.searchBoardNo(session,param);
	}
	//
}
