package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name ="seq_studentNo",sequenceName = "seq_studentNo",allocationSize = 1 )
//json으로  파싱할떄 무한루칭 방지하기
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_studentNo")
	private Long studentNo;
	
	private String studentName;
	
	private Integer grade;
	
	private Integer classNumber;
	
	//다대다 관계를 설정하기
	@ManyToMany
	@JoinTable(name = "student_club",
		joinColumns = @JoinColumn(name="studentNo"),  // 혅 엔티티티의  pk를 참조할 컬럼
		inverseJoinColumns= @JoinColumn(name="clubNo")	// 연결된 상대방 엔티티의 pk를 참조할 컬럼
	)
	private List<Club> clubs;
	
	
	
	
	
}
