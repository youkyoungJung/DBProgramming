<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*, java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<jsp:include page="/navbar.jsp" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>영화 시간표</title>
<style>
h3 {
	font-family: Cafe24Danjunghae;
}

.date {
	color: black;
	/* margin-left : 50px;
			margin-right: 50px;
			margin-top : 20px;
			margin-bottom: 20px; */
	text-align: center;
}

.date-table {
	margin-left: 200px;
	margin-right: 200px;
	margin-top: 30px;
}

.time-table {
	margin-left: 200px;
	margin-right: 200px;
	font-size: 10px;
}

.hr1 {
	height: 2px;
	background: #ccc;
}

a, .title {
	text-decoration: none;
	color: black;
}

.text {
	color: red;
}

.movie-time {
	font-size: 15px;
}

.age {
	color: #cd5c5c;
}

.play {
	color: blue;
	border: 1px solid blue;
	boder-radius: 10px;
}

.plus {
	color: gray;
	font-size: 10px;
}

#btn-movie {
	text-align: center;
}
</style>
</head>
<body>
	<!-- body -->
	<div class="date-table">
		<H3>ERUCHACHA CINEMA 동덕점</H3>
		<hr class="hr1">
		<div class="date">
			<a href="#">◀</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				Calendar cal = Calendar.getInstance();
		      cal.setTime(new Date());
		      DateFormat df = new SimpleDateFormat("MM'월' dd'일'");
		      
		      cal.add(Calendar.DATE, +1);
		      
		      out.print(df.format(cal.getTime()));
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">▶</a>
		</div>
		<hr class="hr1">
	</div>

	<div class="time-table">
		<span class="text"> *시간을 클릭하시면 빠른 예매를 하실 수 있습니다.</span>
		<hr>
		<div class="movie-time">
			<c:forEach var="movie" items="${movies }" varStatus="status">
				<a class="title">${movie.title }</a>
				<span class="play">상영중</span>
				<span class="plus">${movie.genre }/ ${movie.release_date } 개봉</span>
				<br>
				<div>▷ ${movie.theater_name } | 25석</div>
				<div>
					<c:forEach var='time' items="${movie.timeList }">
						<a type="button" class="btn btn-outline-secondary" id="btn-movie" 
						href="<c:url value='/movie/reserve/choice/seat/form'> 
						<c:param name='time' value='${time.time_id }'/></c:url>">${time.time }
							<br> <span>${time.enable }석</span>
						</a>
					</c:forEach>
				</div>
				<br>
				<hr>
			</c:forEach>
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