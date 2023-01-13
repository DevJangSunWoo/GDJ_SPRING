package com.bs.spring.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//자바스크립트객체는 자바객체로 변환해서 쓸수 있음
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMessage {
	private String type;
	private String sender;
	private String reciever;
	private String msg;
	private String room;
	
	
	
}
