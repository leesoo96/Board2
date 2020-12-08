<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="/bDetail" method="post">
	<div>
	번호 : ${contents.i_board} <br/>
	제목 : ${contents.title } <br/>
	내용 : ${contents.ctnt } <br/>
	작성날짜 : ${contents.r_dt } <br/>
	
	<input type="hidden" name="typ" value="${contents.typ }">
	<input type="hidden" name="i_board" value="${contents.i_board }">
	<input type="submit" value="삭제하기">
	</div>
</form>

<a href="/bList?typ=${contents.typ }">돌아가기</a>


<script>
	<c:if test="${msg != null}">
		alert('${msg}');
	</c:if>
</script>