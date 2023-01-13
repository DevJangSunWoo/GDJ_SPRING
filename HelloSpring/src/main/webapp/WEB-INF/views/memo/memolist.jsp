<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>

<section>
 <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
			<c:if test="${not empty memos}">
				<c:forEach var="m" items="${memos}">
					<tr>
						<td><c:out value="${m.memoNo}"/></td>
						<td><c:out value="${m.memo}"/></td>
						<td>
							<%-- <fmt:formatDate var="formatToday" value="${nowDate}" pattern="yyyyMMdd" /> --%>
							<fmt:formatDate value="${m.memoDate}" type="both" dateStyle="full" />
						</td>
						<td>
							<button  class="btn btn-outline-primary my-2 my-sm-0"
						onclick="location.replace('${path}/memo/memo.do');"> 삭제하기</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
        </table>
       <div id="pageBar">
        	${pageBar}
        </div>
        <button  class="btn btn-outline-primary my-2 my-sm-0"
					onclick="location.replace('${path}/memo/memoEnroll.do');"> 작성하기</button>
</section>


<!-- <script type="text/javascript">
	function fn_paging(){
		location.assign()
		
	}
</script> -->




<jsp:include page="/WEB-INF/views/common/footer.jsp"/>