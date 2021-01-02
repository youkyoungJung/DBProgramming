<%@ page contentType="text/html; charset=utf-8" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
  <jsp:include page="/navbar.jsp" />
<script type="text/javascript">
function memberRemove() {
   if (confirm("정말 회원탈퇴를 하시겠습니까?")) {
      return alert("안녕히 가십시오.");
   }
   else {
      return alert("잘 생각하셨습니다");
   }
}
</script>
<html>
<title>마이페이지 - 회원 정보</title>
<style>

#menus {
   font-family: Cafe24Simplehae;
   font-weight: bold;
   font-size: 20px;
}

#text {
   font-family: Cafe24Simplehae;
   font-size: 20px;
}

#buttons {
   width: 15%;
   font-size: 16px;
   float: none;
   margin: 0 auto;
}

table {
   table-layout: fixed;
}

th {
   width: 200px;
}
</style>
</head>

<!-- Custom styles for this template -->
<body class="bg-light">
   <div class="py-5">
      <h2 style="text-align: center;" id="logo">회원 정보</h2>
      <a class="btn float-right btn-danger"
         style="margin-right: 370px; width: 9%;" id="buttons"
         href="<c:url value='/member/delete'>
                 <c:param name='memberId' value='${member.member_id}'/>
              </c:url>"
         onClick="return memberRemove();">회원 탈퇴</a>
   </div>
   <div class="container"
      style="display: flex; justify-content: center; align-items: center;">
      <form name="form" method="POST"
         action="<c:url value='/member/profile' />" class="needs-validation"
         novalidate>
         <table class="table" style="width: 800px">
            <tbody>
               <tr>
                  <th class="table-secondary text-center" id="menus">이름</th>
                  <td id="text">${member.name}</td>
               </tr>
               <tr>
                  <th class="table-secondary text-center" id="menus">아이디</th>
                  <td id="text">${member.member_id}</td>
               </tr>
               <tr>
                  <th class="table-secondary text-center" id="menus">비밀번호</th>
                  <td id="text">***********</td>
               </tr>
               <tr>
                  <th class="table-secondary text-center" id="menus">이메일</th>
                  <td id="text">${member.email}</td>
               </tr>
               <tr>
                  <th class="table-secondary text-center" id="menus">휴대전화</th>
                  <td id="text">${member.tel}</td>
               </tr>
            </tbody>
         </table>
         <hr class="mb-4">
         <div align="center">
            <a class="btn btn-primary btn-dark" id="buttons" style="width: 9%;"
               href="<c:url value='/member/update'>
                 <c:param name='memberId' value='${member.member_id}'/>
              </c:url>">수정</a>&nbsp;
            &nbsp; <a class="btn btn-primary btn-secondary" id="buttons"
               style="width: 9%;"
               href="<c:url value='/movie/list'>
              </c:url>">취소</a>&nbsp;
            &nbsp;
         </div>
      </form>
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