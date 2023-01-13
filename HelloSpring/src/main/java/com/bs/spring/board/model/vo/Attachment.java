package com.bs.spring.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
	private int attachmetnNo;
	//private int boardNo;//
	private Board board;
	private String orginalFileName;
	private String renamedFileName;
	private Date uploadDate;
	private int downloadCount; //
	//private String status;//
	
	
	
}
