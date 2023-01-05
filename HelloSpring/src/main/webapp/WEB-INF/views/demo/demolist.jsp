<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		
		<c:forEach var="dev" items="${demos}" varStatus="vs">
			<tr>
				<td><c:out value="${dev.devNo}"/>	</td>
				<td><c:out value="${dev.devName}"/>	</td>
				<td><c:out value="${dev.devAge}"/>	</td>
				<td><c:out value="${dev.devEmail}"/>	</td>
				<td><c:out value="${dev.devGender}"/>	</td>
				<td>
					<c:forEach var="l" items="${dev.devLang}" >
						${l}
					</c:forEach>		
				</td>
				<td>
					<button class="btn btn-outline-warning"
					onclick="location.replace('${path}/demo/demoUpdate.do?no=${dev.devNo}');"> 수정하기</button>
		<!-- 		onclick="requestSend('demo/demoUpdate.do')">수정하기</button> -->
				</td>
			</tr>
		</c:forEach>
	</table>	
</section>
<!-- <script>
	const requestSend=(url)=>{
		$("#devFrm").attr("action","${path}/"+url+"?no=${dev.devNo}");
		$("#devFrm").submit();
	}


</script> -->



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>