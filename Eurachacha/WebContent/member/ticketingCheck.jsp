<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<jsp:include page="/navbar.jsp" />
<title>마이페이지 - 포인트 확인 및 영화 예매 확인</title>
<script>
function commRemove() {
    return confirm("포인트 적립이 취소됩니다. 정말 삭제하시겠습니까?");      
 }
</script>
<style>
table {
	text-align: left
}

</style>

</head>
<body>

	<div class="container">
		<div class="py-4 text-center">
			<br><h2 id="logo">TICKETING CHECK</h2>
			<br><a style="font-family: Cafe24Simplehae;">고객님의 예매 내역입니다.</a><br>
		</div>

		<c:forEach var="ticket" items="${ticketList}">
			<form name="form" method="POST"
				action="<c:url value='/movie/reserve/ticket/cancel' />">


				<div class="container"
					style="display: flex; justify-content: center;">

					<table class="table" style="width: 400px">
						<tbody>
							<tr>
								<td class="table-secondary text-center" id="menu">티켓 번호 :</td>
								<td class="table-secondary text-left" id="menu">${ticket.ticket_id}</td>
							</tr>
							<tr>
								<td class="text-center" id="menu">영화 :</td>
								<td class="text-left" id="menu">${ticket.title}</td>
							</tr>
							<tr>
								<td class="text-center" id="menu">극장 :</td>
								<td class="text-left" id="menu">${ticket.theater_name}</td>
							</tr>
							<tr>
								<td class="text-center" id="menu">시간 :</td>
								<td class="text-left" id="menu">${ticket.time}</td>
							</tr>
							<tr>
								<td class="text-center" id="menu">좌석 :</td>
								<td class="text-left" id="menu">${ticket.row} -
									${ticket.col}</td>
							</tr>
							<tr>
								<td class="text-center" id="menu">결제 금액 :</td>
								<td class="text-left" id="menu">${ticket.cost}원</td>
							</tr>
							<tr>
								<td colspan="2" class="text-center" id="menu"><input
									type="submit" class="btn btn-primary btn-danger" id="buttons"
									type="button" value="예매 취소" style="width: 50%"
									onclick="return commRemove();"
									></td>

							</tr>
						</tbody>

					</table>

				</div>

				<hr width="80%">
				<input type="hidden" name="ticket_id" value='${ticket.ticket_id }'>
			</form>
		</c:forEach>
		

	</div>


	<!-- 하단 -->
	<hr width="80%">
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