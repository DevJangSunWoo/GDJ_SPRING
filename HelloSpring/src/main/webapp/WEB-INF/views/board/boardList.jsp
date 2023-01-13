<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>

<section id="board-container" class="container">
        <div style="display:flex;justify-content:space-between;align-items:center">
	        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
	        <button class="btn btn-outline-success" 
	        onclick="location.assign('${path}/board/boardWrite.do');">글쓰기</button>
    	</div>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:choose>
	            <c:when test="${not empty boards}">	
	            	<c:forEach var="b" items="${boards}">
		            	<tr>
		            		<td>
		            			<a href="${path}/board/boardView.do?boardNo=${b.boardNo}">
		            				<c:out value="${b.boardNo}"/>
		            			</a>
		            		</td>
		            		<td><c:out value="${b.boardTitle}"/></td>
		            		<td><c:out value="${b.boardWriter.userId}"/></td>
		            		<td>
		            			<fmt:formatDate value="${b.boardDate}" type="both" dateStyle="full" />
		            		</td>
		            		<td>
		            			 <c:if test="${not empty b.files}">
		            			 		<img src="${path}/webapp/resources/images/logo-spring.png"/>		
		            			 </c:if>
		            			  <c:if test="${empty b.files}">
		            			 		파일 없음.
		            			 </c:if>
		            		</td>
		            		<td><c:out value="${b.boardReadCount}"/></td>
		            	</tr>
	            	
	            	</c:forEach>
	            </c:when>
	            <c:otherwise>
	            	조회된 데이터 없음
	            </c:otherwise>
        	</c:choose>
        </table> 
		<div id="pagebar">
			${pageBar}
		
		</div>
</section>





<jsp:include page="/WEB-INF/views/common/footer.jsp"/>