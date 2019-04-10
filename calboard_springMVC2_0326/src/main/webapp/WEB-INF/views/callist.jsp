<%@page import="com.hk.utils.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
	function allChk(bool){
		var chks=document.getElementsByName("chk");
		for(var i=0;i<chks.length;i++){
			chks[i].checked=bool;
		}
	}
</script>
</head>
<body>
<jsp:useBean id="util" class="com.hk.utils.Util" />
<h1>일정목록보기</h1>
<form action="muldel.do" method="post">
<!-- <input type="hidden" name="command" value="muldel"/> -->
<table border="1">
	<col width="50px">
	<col width="50px">
	<col width="300px">
	<col width="200px">
	<col width="200px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allChk(this.checked)" /></th>
		<th>번호</th>
		<th>제 목</th>
		<th>일정</th>
		<th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="5">--작성된 일정이 없습니다.--</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><input type="checkbox" name="chk" value="${dto.seq}"/></td>
					<td>${dto.seq}</td>
					<td><a href="caldetail.do?seq=${dto.seq}">${dto.title}</a></td>
					<td>
						<jsp:setProperty property="toDates" name="util" value="${dto.mdate}"/> 
						<jsp:getProperty property="toDates" name="util"/>
					</td>
					<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy년MM월dd일" /> </td>
				</tr>				
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="5">
			<input type="submit" value="삭제"/>
			<input type="button" value="달력" onclick="location.href='calendar.do'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>

