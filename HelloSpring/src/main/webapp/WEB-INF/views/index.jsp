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

</section>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<!-- 푸터 불러오기 -->