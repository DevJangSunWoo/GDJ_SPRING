package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@SequenceGenerator(name ="seq_majorno",sequenceName = "seq_majorno",initialValue = 1,allocationSize = 1 )
public class Major {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_majorno")
	private Long majorNo;
	
	private String majorName;
	private String professor;

	// 연관관계를  설정하는 어노테이션을 추가
	//애는 join 컬럼이 필요하지 않음??
	//양방향관계??
	//여기에선느 굳이 선언안해도됨
	//그러나 양방향시 선언해줘야하고
	//양방향시  누가 주인지 설정  -> 통산  참조키를 가지고 있는 쪽이 주가 됨
	
	//데이터가 수정되는것이  반영되는것이 막아야함
	//서브 클래스는 mappedBy 속성을 이용해서 주가되는 클래스를 참조
	@OneToMany(mappedBy = "major") // 주클래스의 참조 필드명을 mapperdBy에 작성하면됨
	private List<JpaMember> members;
	
	
}
