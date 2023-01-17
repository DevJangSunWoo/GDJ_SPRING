package com.bs.spring.jpa.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//시퀀스 필요 x
public class StudentClubs {
	
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Club club;
	
	
	@Temporal(TemporalType.DATE)
	private Date  enrollDate;
}
