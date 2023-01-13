package com.bs.spring.board.controller;




import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
//import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import java.io.BufferedInputStream;

//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
		
	private BoardService service;
	
	@Autowired
	public  BoardController(BoardService service) {
		this.service=service;
		
	}
	
	
	
	@RequestMapping("/boardList.do")
	public ModelAndView boardList(ModelAndView mv,
			@RequestParam(value="cPage",defaultValue="1")int cPage,
			@RequestParam(value="numPerpage",defaultValue="5")int numPerpage) {
		
		List<Board> list=service.selectBoardListPage(
				Map.of("cPage",cPage,"numPerpage",numPerpage)
				);
		
		mv.addObject("boards",list);
		
		//페이징 처리하기
		int totalData=service.selectBoardListCount();
		mv.addObject("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));// "boardList.do"는 jsp 상에 자바스크립트에 들어감  
		mv.addObject("totalContents",totalData);
		mv.setViewName("board/boardList");
		return mv;
		
		
		
	}
	
	
	//상세
	//@CookieValue(value="testData",required=false) String testData	
	@RequestMapping("/boardView.do")
	public ModelAndView  selectBoard(ModelAndView mv,int boardNo
			//,HttpServletRequest request,HttpServletResponse response
			,@CookieValue(value="boardRead",required=false) String boardRead) {
		boolean readFlag = false;
//		if(boardRead!=null) {
//			
//		}
//		
//		
//	
//		
//		//조회수 설정
//				//Cookie[] cookies = request.getCookies();
//				//String boardRead="";
//				boolean readFlag = false;
//				if(cookies!=null) {
//					for(Cookie c : cookies) {
//						String name = c.getName();//key값
//						String value = c.getValue();
//						if(name.equals("boardRead")) {
//							boardRead = value;
//							if(value.contains("|"+ boardNo + "|")) {
//								readFlag = true;
//							}						
//							break;
//						}
//					}
//				}
//		
//				if(!readFlag) {
//					//읽은적이 없다 -> 쿠키에 현재 게시글 번호 저장 && 조회수 증가
//					Cookie c = new Cookie("boardRead", (boardRead+ "|" + boardNo + "|"));
//					c.setMaxAge(60*60*24);
//					response.addCookie(c);
//				}	
				
				
				
				// readCount
				//int readCount=service.selectBoardCount(boardNo);
		
				
				Map param=new HashMap();
				param.put("boardNo", boardNo);
			//	param.put("readCount", readCount);
				//Board board=service.searchBoardNo(param, readFlag);
				
				mv.addObject("board",service.selectBoard(boardNo));
				mv.setViewName("board/boardView");
				return mv;
				
				
		
	}
	
	//작성화면으로 전환
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		return "board/boardWrite";
		
	}
	
	
	@RequestMapping("/insertBoard.do")
	public ModelAndView insertBoard(ModelAndView mv ,MultipartFile[] upFile,
			String boardTitle ,String boardContent ,String boardWriter
			,HttpSession session) {
//		log.debug(upFile[0].getName());
//		log.debug(upFile[0].getOriginalFilename());
//		log.debug(upFile[1].getName());
//		log.debug(upFile[1].getOriginalFilename());
		log.debug(boardTitle+" "+boardContent+" "+boardWriter);
		//파일 업로드처리하기
		//저장위치 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//폴더있는지 확인하고 폴더를 생성해주기
		File dir=new File(path);
		if(!dir.exists()) dir.mkdir();
		
		
		// db에 이름 저장하기
		List<Attachment> files=new ArrayList();
		
		// 다중 파일 배열로   저장하기
		for(MultipartFile f :upFile) {
		//리네임드 규칙 생성하기
				if(!f.isEmpty()) {
					// 전송된 파일이 있다면
					//파일 리네임처리 직접하기
					String originalFileName= f.getOriginalFilename();
					String ext=originalFileName.substring(originalFileName.lastIndexOf("."));
					// 중복되지 않는 이름 설정한느 값 지정하기
					SimpleDateFormat sdf= new  SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rnd=(int)(Math.random()*10000)+1;
					String renameFile=sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
					
					//파일 업로드 하기
					try {
						//MultipartFile  클래스가 제공해주는   메소드 이용해서  저장처리			
						 f.transferTo(new File(path+renameFile));
						 
						 //add 메소드를 이요해서 객체추가하기
						 files.add(Attachment.builder()
								 .orginalFileName(originalFileName)
								 .renamedFileName(renameFile)
								 .build());
					}catch(IOException e) {
						e.printStackTrace();
					}	
				}	
		}
		
		//보드로 저장
		Board b=Board.builder()
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.files(files)
				.build();
		
		int result=service.insertBoard(b);
		mv.addObject("msg",result>0?"게시글 등록 성공":"게시글 등록 실패");
		mv.addObject("loc","/board/boardList.do");
		
		mv.setViewName("common/msg");
		
		
		
		return mv;  // 따로 뷰화면을 따로  설정하지 않으면  매핑주소로 나옴
	}
	
	//@RequestHeader(value="User-agent")String haeder   한글로 된 파일이 깨지지 않기 위해
	// HttpServletResponse response    경로를 위해
	@RequestMapping("/filedown.do")
	public void fileDownload(String ori,String re, 
			HttpServletResponse response,
			HttpSession session,
			@RequestHeader(value="User-agent") String header) {
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+re);
		try(FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);){
			ServletOutputStream sos=response.getOutputStream();
			
			//파일명 인코딩하기
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			String encodeFilename="";
			if(isMS) {
				encodeFilename=URLEncoder.encode(ori,"UTF-8");
				encodeFilename=encodeFilename.replaceAll("\\+", "%20");
			}else {
				encodeFilename=new String(ori.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=\""+encodeFilename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
