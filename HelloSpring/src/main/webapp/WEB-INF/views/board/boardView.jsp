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
		 <div id="board-container">
	        <input type="text" class="form-control" placeholder="Á¦¸ñ" name="boardTitle" id="boardTitle"  
	        value="${board.boardTitle}"
	        required>
	        <input type="text" class="form-control" name="boardWriter"  
	         value="${board.boardWriter.userId}"
	        readonly required>
				<c:if test="${not empty board.files}">
		               <c:forEach var="file"  items="${board.files}">   
			                    <button type="button" 
			                    class="btn btn-outline-success btn-block"
			                    onclick="fn_filedownload('${file.orginalFileName}','${file.renamedFileName}');">${file.orginalFileName}</button>
		            </c:forEach> 
	        	</c:if>  
	        <textarea class="form-control" name="boardContent" placeholder="내용" required>${board.boardContent}</textarea>
	    </div> 
	</section>
	
	<script>
		const fn_filedownload=(oriname,rename)=>{
			location.assign("${path}/board/filedown.do?ori="+oriname+"&re="+rename);
		}
	</script>
	
		
<style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>