<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
 
 
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo"/>
</jsp:include>    
    
    
    
    
    
    <style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>
<section id="content">
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td>${demo.devName}</td>
		<tr>
		<tr>
			<th>나이</th>
			<td>${demo.devAge}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${demo.devEmail}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${demo.devGender}</td>
		</tr>
		<tr>
			<th>개발가능 언어</th>
			<td>
			<c:if test="${not empty demo.devLang}">		
				<c:forEach var="lang" items="${demo.devLang}">
				${lang}	
				</c:forEach>
			</c:if>	
			
			</td>
		</tr>
	</table>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	