<%@ page
	import="java.util.Calendar, java.util.*, java.text.DateFormat,
java.text.SimpleDateFormat"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/navbar.jsp" />
<title>영화 선택</title>
<style>
* {
	box-sizing: border-box;
	font-family: 'Cafe24Simplehae';
}

.calendar {
	margin: 0px 0px 0px 20%;
	width: 950px;
	border-collapse: collapse;
}

tr {
	text-align: center;
	font-family: 'Cafe24Danjunghae';
}

th {
	text-align: center;
	font-family: 'Cafe24Danjunghae';
}

h4 {
	margin: 0px 0px 0px 20%;
	font-family: 'Cafe24Danjunghae'
}

ul.movies li, ol.movies li {
	padding: 5px 0px 5px 5px;
	margin-bottom: 5px;
	border-bottom: 1px solid #efefef;
	width: 200px;
	margin: 0px 0px 0px 20%;
	font-size: 15px;
	font-family: 'Cafe24Danjunghae';
	list-style-type: none;
}

ul.movies li, ol.movies li {
	-webkit-transition: background-color 0.3s linear;
	-moz-transition: background-color 0.3s linear;
	-ms-transition: background-color 0.3s linear;
	-o-transition: background-color 0.3s linear;
	transition: background-color 0.3s linear;
}

ul.movies li:hover, ol.movies li:hover {
	background-color: #f6f6f6;
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

ul.register_information, ol.register_information {
	list-style: none;
	margin: 0px;
	padding: 0px;
	max-width: 900px;
	width: 100% font-family: 'Cafe24Danjunghae';
	margin: 0px 0px 0px 20%;
}

ul.register_information li, ol.register_information li {
	width: 150px;
	display: inline-block;
	padding: 20px;
	margin-bottom: 5px;
	border: 1px solid #efefef;
	font-size: 15px;
	cursor: pointer;
	font-family: 'Cafe24Danjunghae';
}

ul.register_information li, ol.register_information li {
	-webkit-transition: background-color 0.3s linear;
	-moz-transition: background-color 0.3s linear;
	-ms-transition: background-color 0.3s linear;
	-o-transition: background-color 0.3s linear;
	transition: background-color 0.3s linear;
}

ul.register_information li:hover, ol.register_information li:hover {
	background-color: #f6f6f6;
}

li.time {
	text-align: center;
}

</style>
<script>
	
</script>
</head>
<body>
	<br>
	<%
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy'년' MM'월' dd'일'");

		cal.add(Calendar.DATE, +1);

		out.print("<h4>" + df.format(cal.getTime()) + "의 영화를 예매합니다. </h4>");
	%>
	<br>

	<div align="left">
		<h4 class="movie_select">영화 선택</h4>
	<br>
		<form method="POST" action="<c:url value='/movie/timelist' /> ">
			<ul class="movies">

				<c:forEach var="movie" items="${movieList}" varStatus="i">
					<c:choose>
						<c:when test="${i.first }">
							<li>${movie.title}&nbsp;&nbsp;&nbsp;<input type="radio"
								name="movie_id" value=${movie.movie_id } checked="checked">
							</li>
						</c:when>

						<c:otherwise>
							<li>${movie.title}&nbsp;&nbsp;&nbsp;<input type="radio"
								name="movie_id" value=${movie.movie_id} >
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</ul>
			<div align="center">

				<input type="submit" class="submit" value="영화 선택">
			</div>

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