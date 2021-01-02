<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>돌려돌려 돌림판</title>
<meta charset="utf-8">
<jsp:include page="/navbar.jsp" />

<script type="text/javascript"
	src="<c:url value='/js/jquery-1.11.3.min.js' /> "></script>
<script type="text/javascript"
	src="<c:url value='/js/jQueryRotateCompressed.js '/> "></script>
<style>
#image {
	z-index: 10;
}

#n_id {
	z-index: 20;
}

* {
	box-sizing: border-box;
	font-family: 'Cafe24Simplehae';
}

.start_btn {
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

	<div align="center">
		<br> <br>
		<h3>랜덤 포인트</h3>

		<br> <img src="<c:url value='/roulette/niddle.png' />" id="n_id">
		<br> <img src="<c:url value='/roulette/roulette.png' />"
			id="image"> <br> <input type='button' value='시작'
			class="submit" id='start_btn'></input>
		<div id="result_id"></div>
		<div id="result_id2"></div>
		<div id="result_id3"></div>

		<script>
		var data;
		

				var pArr = [ "100", "10", "20", "30", "0", "50", "60", "70", "80", "90" ];

				$('#start_btn').click(function() {
					rotation();
				});

				function rotation() {
					$("#image").rotate({
						angle : 0,
						animateTo : 360 * 5 + randomize(0, 360),
						center : [ "50%", "50%" ],
						easing : $.easing.easeInOutElastic,
						callback : function() {
							var n = $(this).getRotateAngle();
							endAnimate(n);
						},
						duration : 5000
					});
				}

				function endAnimate($n) {
					var n = $n;
					
				
					var real_angle = n % 360 + 18;
					var part = Math.floor(real_angle / 36);

			

					if (part < 1) {
						$('#result_id3').html("<br><br><h4>당첨내역:" + pArr[0] + "</h4>");
						data = pArr[0];
						return;
					}

					if (part >= 10) {
						$('#result_id3').html(
								"<br><br><h4>당첨내역:" + pArr[pArr.length - 1] + "</h4>");
						data = pArr[pArr.length - 1]
						return;
					}

					$('#result_id3').html("<br><br><h4>당첨내역:" + pArr[part] + "</h4>");
					
					data = pArr[part];
				}

				function randomize($min, $max) {
					return Math.floor(Math.random() * ($max - $min + 1)) + $min;
				}
			
			

			function sendPoint(targetUri){
				  location.href=targetUri + "?point=" + data;
				}
		</script>

		<br> <br>
		<form>
			<input type="button" value="확인" class="submit"
				onClick="sendPoint('<c:url value='/movie/reserve/ticket/point'/>') ">

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

