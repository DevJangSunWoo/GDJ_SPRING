package com.bs.spring.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//websocket서버로 활용되는 pojo
//TextWebSocketHandler 클래스를 상속 받아서 구현.
//alt shift s v
//이제 빈으로 등록하자
@Slf4j
public class ChattingServer extends  TextWebSocketHandler{

	
	//#2 저장할 장소  //  동일한 아이디로  똑같은 클라이런트로 저장되는 것을 막을려고 Map 을 사용	
	private Map<String,WebSocketSession> clients =new HashMap();
	
	
	//빈 config
	private ObjectMapper mapper;
	
	@Autowired
	public void setMapper(ObjectMapper mapper) {
		this.mapper=mapper;
		
	}
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		log.debug("클라이언트 접속");
	}

	
	
	

	//WebSocketSession session  은  json session과 다르다
	//보낸사람만 알수 있다.
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//클라이언트가 보낸 메세지 확인하기
		//클라이언트가 보낸 메세지는 payload에서 저장된다.	
		log.debug("{}",message.getPayload());
		//자바스크립트객체는 자바객체로 변환해서 쓸수 있음
		// 클라이언트가 보낸  json 데이터를 jackson 이용해서 파싱하기
		
		//ObjectMapper mapper=new  ObjectMapper();
		
		SendMessage msg=mapper.readValue(message.getPayload(),SendMessage.class);
		log.debug("{}",msg);
		
		
		// open하면 세션은 내가 관리해야함
		switch(msg.getType()) {
		case "open" :addClient(session,msg);break;  // client 정보에 추가
		case "msg" :sendMessage(msg);break; // 메세지를 클라이언트에게 전달
		case "system" :sendAdminMessage();break; // 시스템정보를 클라이언트에게 전달
		
		}
		
	}

	
	//#1 외부에서 이용하는게 아니니까  내부에 선ㅇ너
	private void addClient(WebSocketSession session,SendMessage msg)  throws IOException{
			session.getAttributes().put("info", msg);
			clients.put(msg.getSender(), session);   // 클라이언트에 있는 애들을 보내버림
			SendMessage adminmsg=new SendMessage("system","","",msg.getSender()+"가 접속했습니다","");
			
		//	ObjectMapper mapper=new  ObjectMapper();  // 나중에 전역변수 로 뺴기
			
			for(String id:clients.keySet()) {
				WebSocketSession client=clients.get(id);
				client.sendMessage(new TextMessage(mapper.writeValueAsBytes(adminmsg)));
			}
	}
	
	
	// #2send message
	// msg에는   보낸 시림 받는 사람을 구분해야함 .          client에 해당하는 사람의 정보를 계속 저장하고 있다.
	private void sendMessage(SendMessage msg) throws IOException {
		
		for(String id:clients.keySet()) { 
			WebSocketSession client=clients.get(id);
			client.sendMessage(new TextMessage(mapper.writeValueAsBytes(msg)));
		}
		
	}
	
	private void sendAdminMessage() {
		
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	
}
