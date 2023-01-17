package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name ="seq_clubNo",sequenceName = "seq_clubNo",allocationSize = 1 )
public class Club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_clubNo")
	private Long clubNo;
	
	private String name;
	
	private String location;
	
	
	@ManyToMany(mappedBy="clubs")
	private List<Student> students;
	
	
	
	
}
