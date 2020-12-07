<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link rel="stylesheet" href="res/css/bList.css?ver=2">
<div>
	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성날짜</td>
		</tr>
		<c:forEach items="${list }" var="item">
			<tr class="pointer" onclick="clickItem(${typ }, ${item.i_board });">
				<td>${item.i_board}</td>
				<td>${item.title}</td>
				<td>${item.r_dt}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="/bRegmod?typ=${typ }"><button>WRITE</button></a>
	</div>
</div>

<script>
	function clickItem(typ, i_board){
//		console.log('typ = ' + typ + ' / i_board = ' + i_board);
//		var url = '/bDetail?typ=' + typ + '&i_board=' + i_board;

//      ``를 쓸때는 값이 아닌 문장 그대로를 받도록 해야하기때문에 \를 붙여준다 -> 그래야 제대로 올바르게 동작한다!!!
		var url = `/bDetail?typ=\${typ}&i_board=\${i_board}`;
		location.href = url; 
	}
</script>