<%@ page contentType="text/html; charset=utf-8" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
  <jsp:include page="/navbar.jsp" />
<script>
function memberCreate() {
	if (form.name.value == "") {
		alert("이름은 필수 입력 항목입니다.");
		form.name.focus();
		return false;
	}
	if (form.memberId.value == "") {
		alert("아이디는 필수 입력 항목입니다.");
		form.memberId.focus();
		return false;
	}
	if (form.user_pw.value == "") {
		alert("비밀번호는 필수 입력 항목입니다.");
		form.user_pw.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if (form.tel.value != "") {
		if (phoneExp.test(form.tel.value) == false) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.tel.focus();
			return false;
		}
	} else {
		alert("전화번호를 입력해주세요.");
		form.tel.focus();
		return false;
	}
	if(form.user_pw2.value == "") {
		alert("비밀번호 확인은 필수입니다.");
		form.user_pw2.focus();
		return false;
	} else {
		if (form.user_pw.value != form.user_pw2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.user_pw2.focus();
			return false;
		}
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (form.email.value != "") {
		if(emailExp.test(form.email.value) == false) {
			alert("이메일 형식이 올바르지 않습니다.");
			form.email.focus();
			return false;
		}
	} else {
		alert("이메일을 입력해주세요.");
		form.email.focus();
		return false;
	}

	form.submit();
}
function login(targetUri) {
	form.action = targetUri;
	form.submit();	
}
</script>
<html>
<title>마이페이지 - 회원 가입</title>
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
	<div class="py-5 text-center">
		<h2 id="logo">회원 가입</h2>
	</div>
	<div class="container"
		style="display: flex; justify-content: center; align-items: center;">
		<div>
			<form name="form" method="POST"
				action="<c:url value='/member/join' />" class="needs-validation"
				novalidate name="profile">
				<div class="row">
					<div class="col-md-12">
						<label for="name" id="menu">이름</label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="text" class="form-control" placeholder="이름" value=""
							width="50%" name="name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label for="id" id="menu">아이디</label>
						<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
					<c:if test="${registerFailed}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>
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
							value="" name="user_pw" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label for="password" id="menu">비밀번호 확인 <span
							class="text-muted"></span></label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="password" class="form-control" placeholder="비밀번호"
							value="" name="user_pw2" required>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<label for="email" id="menu">Email</label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="text" class="form-control"
							placeholder="you@example.com" name="email">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label for="phoneNumber" id="menu">휴대전화</label>
					</div>
					<div class="col-md-12 mb-3">
						<input type="text" class="form-control"
							placeholder="예) 010-xxxx-xxxx" value="" name="tel" required>
					</div>
				</div>
				<hr class="mb-3">
				<div style="text-align: center;">
					<button class="btn btn-primary btn-secondary" id="buttons"
						type="button" style="width: 30%" onClick="memberCreate()">회원가입</button>
					&nbsp; &nbsp;
					<button class="btn btn-primary btn-secondary" id="buttons"
						type="submit" style="width: 20%"
						onClick="login('<c:url value='/member/login/form' />')">취소</button>
				</div>
			</form>
		</div>
	</div>
	<footer class="my-1 pt-3 text-muted text-center text-small">
		<p class="mb-1">&copy; 2020 EURACHACHA CINEMA</p>
		<ul class="list-inline">
			<li class="list-inline-item"><a href="#">Privacy</a></li>
			<li class="list-inline-item"><a href="#">Terms</a></li>
			<li class="list-inline-item"><a href="#">Support</a></li>
		</ul>
	</footer>
</body>
</html>