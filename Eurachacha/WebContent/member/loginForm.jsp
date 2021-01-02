<%@ page contentType="text/html; charset=utf-8" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
  <jsp:include page="/navbar.jsp" /><script>
function login() {
	if (form.memberId.value == "") {
		alert("아이디를 입력하십시오.");
		form.memberId.focus();
		return false;
	} 
	if (form.memberPwd.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.memberPwd.focus();
		return false;
	}		
	form.submit();
}

function memberCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<html>
<title>로그인</title>
<style>
#buttons {
	width: 15%;
	font-size: 16px;
	float: none;
	margin: 0 auto;
}

@media ( min-width : 1200px) {
	.container {
		width: 1170px;
	}
}
</style>
</head>

<!-- Custom styles for this template -->
<body class="bg-light">
<body>
	<br>
	<!-- login form  -->
	<div class="py-4 text-center">
		<h2 id="logo">로그인</h2>
	</div>
	<div class="container"
		style="display: flex; justify-content: center; align-items: center;">
		<div>
			<form name="form" method="POST"
				action="<c:url value='/member/login' />">
				<div class="row">
					<div class="col-md-12">
						<label for="id" id="menu">아이디</label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="text" class="form-control" name="memberId" value=""
							placeholder="아이디">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label for="password" id="menu">비밀번호 <span
							class="text-muted"></span></label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="password" class="form-control" placeholder="비밀번호"
							value="" name="memberPwd" required>
					</div>
				</div>
				<c:if test="${loginFailed}">
					<br>
					<font color="red"><c:out value="${exception.getMessage()}" /></font>
					<br>
				</c:if>
				<hr class="mb-4">
				<div style="text-align: center;">
					<button class="btn btn-primary btn-dark" id="buttons" type="button"
						onClick="login()" style="width:30%">로그인</button>
					&nbsp; &nbsp;
					<button class="btn btn-primary btn-secondary" id="buttons"
						type="button" style="width:35%"
						onClick="memberCreate('<c:url value='/member/join/form' />')">회원가입</button>
				</div>
				<footer class="my-1 pt-3 text-muted text-center text-small">
					<p class="mb-1">&copy; 2020 EURACHACHA CINEMA</p>
					<ul class="list-inline">
						<li class="list-inline-item"><a href="#">Privacy</a></li>
						<li class="list-inline-item"><a href="#">Terms</a></li>
						<li class="list-inline-item"><a href="#">Support</a></li>
					</ul>
				</footer>
			</form>
		</div>
	</div>
</body>
</html>