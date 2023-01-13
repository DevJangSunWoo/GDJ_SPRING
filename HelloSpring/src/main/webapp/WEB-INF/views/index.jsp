<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<!-- 헤더파일 불러오기
title 값을 전달해거 출력해야함 ->Main Page 가 출력 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>
	
<section id="content">
	<h2>Hello Sprig</h2>
	<img src="${path}/resources/images/logo-spring.png" id="center-image" alt="스프링로고">

		<div>
			<button onclick="fn_memberlist();">회원조회</button>
		</div>
		<div id="membercontainer">
		
		
		</div>
		<button onclick="jsonTest();">회원가입</button>
</section>
<script>
	function jsonTest(){
		//비동기식통신을 할떄 js기본제공하는 함수 이용하기.
		//fetch() 함수를 제공함 , 동기식 통신을 위해서 promise 객체로 번환을 함.
		//fetch(url주소,옵션(전송))   //405 에러는  방식을 안정했을떄  
		//.then(response=>response.json())    //   response응답 헤더의 정보와 데이터    응답에 대한 결과를 볼수 있음 //  response.json())
		//.then(data=>{로직});
		
		fetch("${path}/member/ajax/insert",{
			method:"post",
			headers:{
				"Content-Type" :"application/json"
			},// 요청 헤더 설정
			body:JSON.stringify({userId:"test22",password:"1234",userName:"test22"})
			// 전송 데이터
		})
		.then(response=>{console.log(response); return response.json(); })
		.then(data=>{ //success callback
		 console.log(data);	
		});
		
	}



 const  fn_memberlist=()=>{
	 $.get("${path}/member/memberList.do",
			 data=>{
				 console.log(data);
				 if(data!=null){
					 console.log("길이"+data.length);	 
					 const table=$("<table>");
					 const header=$("<tr>");
					 header.append("<th>아이디</th>")
					 .append("<th>이름</th>")
					  .append("<th>나이</th>")
					   .append("<th>성별</th>")
					    .append("<th>전화번호</th>")
					     .append("<th>이메일</th>")
					      .append("<th>주소</th>")
					       .append("<th>취미</th>")
					        .append("<th>가입일</th>");
					table.append(header);	
					 data.forEach(e=>{
						 let tr=$("<tr>");
						 let userId=$("<td>").text(e.userId);
						 let name=$("<td>").text(e.userName);
						 let age=$("<td>").text(e.age);
						 let gender=$("<td>").text(e.gender);
						 let phone=$("<td>").text(e.phone);
						 let email=$("<td>").text(e.email);
						 let address=$("<td>").text(e.address);
						 let hobby=$("<td>").text(e.hobby.toString());
						 let enrolldate=$("<td>").text(new Date(e.enrollDate));
						 tr.append(userId).append(name).append(age).append(gender)
							.append(phone).append(email).append(address).append(hobby)
							.append(enrolldate);
							table.append(tr); 
					 });	
					 
					 $("#membercontainer").html(table);
					 
				 }else{
					 $("#membercontainer").text("조회되는 회원이 없습니다.");
					 
				 }
				 
				 
			 });
 	}

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<!-- 푸터 불러오기 -->