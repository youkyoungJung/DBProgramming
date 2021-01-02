<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<!-- CSS only -->
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
   integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
   crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
   integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
   crossorigin="anonymous"></script>
<script
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
   integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
   crossorigin="anonymous"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
   integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
   crossorigin="anonymous"></script>
<style>
@font-face {
	font-family: 'Cafe24Danjunghae';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Danjunghae.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'Cafe24Simplehae';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Simplehae.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

#logo {
	font-family: Cafe24Danjunghae;
}

#menu {
	font-family: Cafe24Simplehae;
	font-weight: bold;
}

nav {
	margin-left: 30px;
	margin-right: 30px;
}
</style>
</head>
<body>
<!-- navbar -->
   <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #778899;">
     <div class="container-xl">
     <a class="navbar-brand" href="<c:url value='/movie/list'/>">
       <img src="<c:url value='/img/logo.png' />" width="50" height="40" class="d-inline-block align-top" alt="" loading="lazy"> </a>
        <a class="navbar-brand" id="logo" href="<c:url value='/movie/list'/>">EURACHACHA CINEMA</a>
       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
       </button>
   
       <div class="collapse navbar-collapse" id="navbarsExample07XL">
         <ul class="navbar-nav mr-auto">
           <li class="nav-item active" id="menu">
             <a class="nav-link" href="<c:url value='/movie/list'/>">Home <span class="sr-only">(current)</span></a>
           </li>
           <li class="nav-item" id="menu">
             <a class="nav-link" href="<c:url value='/movie/list'/>">영화</a>
           </li>
           <!-- <li class="nav-item">
             <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
           </li> -->
           <li class="nav-item dropdown" id="menu">
             <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">예매</a>
             <div class="dropdown-menu" aria-labelledby="dropdown07XL">
               <a class="dropdown-item" href="<c:url value='/movie/timetable'/>">상영 시간표</a>
               <a class="dropdown-item" href="<c:url value='/movie/playingmovie'/>">예매하기</a>
             </div>
           </li>
           <li class="nav-item dropdown" id="menu">
             <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">마이페이지</a>
             <div class="dropdown-menu" aria-labelledby="dropdown07XL">
              <c:if test="${isLogin}">
               <a class="dropdown-item" href="<c:url value='/member/profile'><c:param name='memberId' value='${memberId}'/>
			 	 </c:url>">회원정보</a>
               <a class="dropdown-item" href="<c:url value='/member/reserve/check'><c:param name='memberId' value='${memberId}'/>
				</c:url>">예매 확인</a>
               <a class="dropdown-item" href="<c:url value='/member/point'><c:param name='memberId' value='${memberId}'/>
               </c:url>">포인트 확인</a>
               <div class="dropdown-divider"></div>
               </c:if>
                <c:if test="${!isLogin}">
                  <a class="dropdown-item" href="<c:url value='/member/login/form'/>">로그인</a>
               </c:if>
               <c:if test="${isLogin}">
                  <a class="dropdown-item" href="<c:url value='/member/logout'/>">로그아웃</a>
               </c:if>
             </div>
           </li>
         </ul>
         <form class="form-inline my-2 my-md-0" id="menu" action="<c:url value='/movie/search'/>">
           <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
         </form>
       </div>
     </div>
   </nav>
</body>
</html>