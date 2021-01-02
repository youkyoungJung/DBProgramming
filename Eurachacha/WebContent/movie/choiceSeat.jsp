<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <jsp:include page="/navbar.jsp" />
  <title>좌석 선택</title>
  <link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/gh/hiphop5782/js@0.0.14/cinema/hacademy-cinema.css">
	
<script
	src="https://cdn.jsdelivr.net/gh/hiphop5782/js@0.0.13/cinema/hacademy-cinema.js"></script>

<style>
* {
	box-sizing: border-box;
	font-family: 'Cafe24Simplehae';
}

.float-box>div {
	float: center;
	width: 70%;
	height: 40%;
}

.float-box::after {
	content: "";
	display: block;
	clear: both;
}

.float-box>.result {
	padding: 0.5rem;
}

.submit {
	width: 120px;
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

.screen {
	border: 1px solid;
	padding: 10px;
	width: 60%;
}
</style>
<script
	src="https://cdn.jsdelivr.net/gh/hiphop5782/js@0.0.13/cinema/hacademy-cinema.js"></script>
<script>
	window.addEventListener("load", function() {
		var cinema = new Hacademy.Reservation("#cinema");
		cinema.addChangeListener(function(seat) {
			print(this);
		});
		print(cinema);
		function print(app) {
			document.querySelector(".result").textContent = app
					.getQueryString();
		}
	});
</script>
</head>
<body>
	<br>


	<c:if test="${!empty seat_choice_error}">
		<script>
			alert('${seat_choice_error }')
	</script>
	</c:if>

	<br>
	<div align="center">
		<h5>* 화면을 작게 하여 보시는 것을 추천드립니다.</h5>
	</div>
	<br>
	<!-- SCREEN 박스 -->
	<div align="center">
		<div class="screen" align="center">
			<h2>SCREEN</h2>
		</div>
	</div>

	<!-- 좌석 -->

	<br>
	<div class="float-box" align="center">
		<div>
			<form action="<c:url value='/movie/reserve/ticket' /> " method="POST" >
				<div id="cinema" class="cinema-wrap" data-name="seat">
					<div class="cinema-seat-area" data-rowsize="5" data-colsize="5"
						data-mode="manager" data-fill="manual" data-seatno="visible">

						<%
							for (int i = 1; i <= 5; i++) {
								for (int j = 1; j <= 5; j++) {
						%>
						
						<div class="cinema-seat" data-row=<%=i%> data-col=<%=j%>></div>

							<c:forEach var="unable" items="${unable}">
								<div class="cinema-seat" data-state="disabled"
									data-row=${unable.row } data-col=${unable.col}></div>
							</c:forEach>
						<%
							}
							}
						%>
					</div>
				</div>
				
				<!-- 시간값 전달 -->
				<input type="hidden" name="time" value=${time} >

				<br> <input type="submit" class="submit" value="선택" >
			</form>
		</div>

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