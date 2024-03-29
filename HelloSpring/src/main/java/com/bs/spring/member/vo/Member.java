package com.bs.spring.member.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//빌더 사용일 거의 없다
public class Member implements UserDetails {

	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	
	// getAuthorities   
	//사용자의 권한을 등록해주는 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//SimpleGrantedAuthority 클레스를 이용해서 권한을 등록
		
		List<GrantedAuthority> auth =new ArrayList();
		auth.add(new SimpleGrantedAuthority("ROLE_USER") );
		if(userId.equals("admin"))auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		
		
		return auth;
	}
	//true 가나오면   인증처리하고  이중에 하나라도 false 인증미처리하기
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
