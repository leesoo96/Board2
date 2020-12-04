<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<h2>글쓰기</h2>
<form action="/bRegmod" method="post" id="frm" onsubmit="return chk();">
	<input type="hidden1" name="i_board" value="${param.i_board }">
	<input type="hidden1" name="typ" value="${typ }">
	<div>
		제목 <input type="text" name="title" value="${data.title }">
	</div>
	<div>
		내용 <textarea name="ctnt"${data.ctnt }></textarea>
	</div>
	<div>
		<input type="submit" value="확인">
	</div>
</form>

<script>
	function chk(){
		if(chkEmptyEle(frm.title, '제목') || chkEmptyEle(frm.ctnt, '내용')){
			return false;
		}
	}
	/* /bRegmod의 doPost() -> err  */
	<c:if test="${err != null}">
		alert('${err}');
	</c:if>
</script>