<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" href="res/css/bDetail.css?ver=3">
</head>
<body>
<form action="/bDetail" method="post">
	<input type="submit" value="삭제하기">

  <div id="contents">
	<h2>${contents.typ }</h2>
	<p>제목 : ${contents.title }</p> 
	<p>내용 : ${contents.ctnt }</p> 
	<span>글번호 : ${contents.i_board}</span><br/>
	<span>작성날짜  : ${contents.r_dt }</span><br/>
  </div>
</form>
  <a href="/bList?typ=${typ }>">BACK</a>
</body>
</html>