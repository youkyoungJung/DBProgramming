<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
  <jsp:include page="/navbar.jsp" />
<title>마이페이지 - 포인트 확인 및 영화 예매 확인</title>
</head>
<body>
   <form name="form" method="POST" action="<c:url value='/member/point' />">
   <div class="container">
      <div class="py-5 text-center">
        <h2 id="logo">EURACHACHA POINT</h2>
        <br><a id="menu">"${memberId}" 님의 포인트&nbsp; &nbsp; &nbsp; &nbsp;</a>${member.point}<a id="menu">
            점</a>
      </div>
   
   <hr width="80%">
   <footer class="my-3 pt-3 text-muted text-center text-small">
      <p class="mb-1">&copy; 2020 EURACHACHA CINEMA</p>
      <ul class="list-inline">
         <li class="list-inline-item"><a href="#">Privacy</a></li>
         <li class="list-inline-item"><a href="#">Terms</a></li>
         <li class="list-inline-item"><a href="#">Support</a></li>
      </ul>
   </footer>
   </div>
   </form>
</body>
</html>