<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>

<form action="${path}/login" method="post">
						<div class="modal-body">
							<input type="text" name="userId" class="form-control"
							placeholder="아이디입력" required><br>
							<input type="password" name="password" class="form-control"
							placeholder="패스워드 입력"  required>										
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-outline-success">로그인</button>
							<button type="button" class="btn btn-outline-success"
							data-dismiss="modal">취소</button>
						</div>
					</form>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>