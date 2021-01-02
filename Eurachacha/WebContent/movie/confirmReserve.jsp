<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/navbar.jsp" />
<title>예매확인</title>
<style>
h4 {
	margin: 0px 0px 0px 20%;
	font-family: 'Cafe24Danjunghae'
}

h5 {
	margin: 0px 0px 0px 20%;
	font-family: 'Cafe24Danjunghae'
}

.submit {
	width: 140px;
	height: 40px;
	color: #fff;
	background: #004fff;
	font-size: 16px;
	border: none;
	border-radius: 20px;
	box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
	transition: 0.3s;
	position: apsolute;
	font-family: 'Cafe24Danjunghae'
}

.submit:focus {
	outline: 0;
}

.submit:hover {
	background: rgba(0, 79, 255, 0.9);
	cursor: pointer;
	box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}
</style>
</head>
<body>
	<br>
	<br>
	<div align="center">


		<div class="container mt-1"
			style="padding-left: 70px; padding-right: 20px">


			<img src="<c:url value = '/img/success.png'/>" width="50%"
				align="middle" border=" " alt="success" />
		</div>
		<br> <br>
		<div align="left" class="none">
			<h4>예매완료</h4>
			<br>
			<h5>예매가 완료되었습니다. 즐거운 관람 되십시오</h5>
			<h5 style="color: red;">♥</h5>
			<br>

		</div>
	</div>

	<div align="center">
		<form action="<c:url value='/member/reserve/check' />">
			<input type="submit" class="submit" value="예매 내역 확인하기">
		</form>
	</div>
	<hr class="mb-4">
	<footer class="my-3 pt-3 text-muted text-center text-small">
		<p class="mb-1">&copy; 2020 EURACHACHA CINEMA</p>
		<ul class="list-inline">
			<li class="list-inline-item"><a href="#">Privacy</a></li>
			<li class="list-inline-item"><a href="#">Terms</a></li>
			<li class="list-inline-item"><a href="#">Support</a></li>
		</ul>
	</footer>

</body>
</html>