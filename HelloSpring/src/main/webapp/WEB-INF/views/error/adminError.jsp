<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"    isErrorPage="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
	
	<h2  style="color:red" >  관리자만 접근할수 있다.</h2>
	<h2>  exception 이엘테그로 접근이 안된디.</h2>
	<script>
		setTimeout(()=>{location.replace("${path}")},3000);
	</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>