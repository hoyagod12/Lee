<%@page import="com.hk.caldtos.CalDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<%
	//텍스트로 되어있는 파라미터는 request.getParameter("seq")
// 	Object obj=request.getAttribute("dto");
// 	CalDto dto=(CalDto)obj;
// 	CalDto dto=(CalDto)request.getAttribute("dto");
%>
<body>
<jsp:useBean id="util" class="com.hk.utils.Util"/>
<h1>일정상세보기</h1>
<table border="1">
	<tr>
		<th>아이디</th>		
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>일정</th>
		<td><jsp:setProperty property="toDates"  name="util" value="${dto.mdate}"/> 
		    <jsp:getProperty property="toDates" name="util"/>
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" onclick="updateForm(${dto.seq})"/>
			<input type="button" value="삭제" onclick="delBoard(${dto.seq})"/>
			<input type="button" value="달력" onclick="calendar()"/>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function updateForm(seq){
		location.href="calupdateform.do?seq="+seq;
	}
	function delBoard(seq){
		location.href="muldel.do?chk="+seq;
	}
	function calendar(){
		location.href="calendar.do";
	}
</script>
</body>
</html>












