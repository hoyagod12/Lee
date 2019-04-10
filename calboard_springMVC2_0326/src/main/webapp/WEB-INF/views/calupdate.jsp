<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>일정수정하기</h1>
<form action="calupdate.do?seq=${dto.seq}" method="post">
<!-- <input type="hidden" name="command" value="calupdate"/> -->
<%-- <input type="hidden" name="seq" value="${dto.seq}"/> --%>
<table border="1">
		
	<tr>
		<th>아이디</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>일정</th>
		<td>
			<select name="year">
				<c:set var="yearParam" value="${fn:substring(dto.mdate,0,4)}" />
				<c:forEach begin="${yearParam-5}" end="${yearParam+5}" step="1" var="i">
					<option value="${i}" ${yearParam==i?"selected":""}>${i}</option>
				</c:forEach>
			</select>년
			<select name="month">
				<c:set var="monthParam" value="${fn:substring(dto.mdate,4,6)}" />
				<c:forEach begin="1" end="12" step="1" var="i">
					<option value="${i}" ${monthParam==i?"selected":""}>${i}</option>
				</c:forEach>
			</select>월
			<select name="date">
				<c:set var="dateParam" value="${fn:substring(dto.mdate,6,8)}" />
				<c:forEach begin="1" end="31" step="1" var="i">
					<option value="${i}" ${dateParam==i?"selected":""}>${i}</option>
				</c:forEach>
			</select>일
			<select name="hour">
				<c:set var="hourParam" value="${fn:substring(dto.mdate,8,10)}" />
				<c:forEach begin="0" end="23" step="1" var="i">
					<option value="${i}" ${hourParam==i?"selected":""}>${i}</option>
				</c:forEach>
			</select>시
			<select name="min">
				<c:set var="minParam" value="${fn:substring(dto.mdate,10,12)}" />
				<c:forEach begin="0" end="59" step="1" var="i">
					<option value="${i}" ${minParam==i?"selected":""}>${i}</option>
				</c:forEach>
			</select>분
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="${dto.title}"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" name="content">${dto.content}</textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료" />
			<input type="button" value="달력" onclick="location.href='calendar.do'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>