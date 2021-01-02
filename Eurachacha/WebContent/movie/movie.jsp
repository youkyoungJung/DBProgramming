<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/navbar.jsp" />
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/swiper.css' />">
<style>
	.notice{
		color: red;
	}
</style>
<script language="JavaScript">
     
   function MvDetail(){
         infor_btn.action = "/movie/detail";
         infor_btn.submit();
         
      }
   </script>

<title>영화</title>
</head>
<body>
	<!-- movie 시작-->
	<section id="movie">
		<h2 class="ir_so">Eurachacha 영화 정보</h2>
		<div class="container">
			<div class="row">
				<div class="movie">
					<div class="movie_title">
						<div class="notice">
						※보고싶은 영화는 "aabbcc@gmail.com" 으로 보내주세요! 보내주시면 추첨을 통해 상영의 기회를 드립니다※
						</div>
						<br>
						<ul>
							<li class="active"><a href="#">영화1</a></li>
							<li><a href="#">영화2</a></li>
							<li><a href="#">영화3</a></li>
							<li><a href="#">영화4</a></li>
						</ul>
					</div>
					<%
                  int i = 1;
               %>
					<div class="movie_chart">
						<div class="chart_cont1">
							<c:forEach var="movie" items="${movieList}" begin="0" end="3">
								<div>
									<div class="poster">
										<figure>
											<img src="<c:url value='${movie.movie_img}' />"
												alt=${movie.title } width=100%>
										</figure>
										<div class="rank">
											<strong><%=i++%></strong>
										</div>
									</div>
									<div class="infor">
										<h3>
											<strong>${movie.title}</strong>
										</h3>
										<div class="infor_btn">
											<a
												href="<c:url value='/movie/detail'>
                             
                           <c:param name='movie_id' value='${movie.movie_id}'/>
                             </c:url>">
												상세정보</a> <a
												href="<c:url value='/movie/timelist'>
                              <c:param name='movie_id' value='${movie.movie_id}' />
                              </c:url> ">
												예매하기</a>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>

						<div class="chart_cont2">
							<c:forEach var="movie" items="${movieList}" begin="4" end="7">
								<div>
									<div class="poster">
										<figure>
											<img src="<c:url value='${movie.movie_img}' />"
												alt=${movie.title } width=100%>
										</figure>
										<div class="rank">
											<strong><%=i++%></strong>
										</div>
									</div>
									<div class="infor">
										<h3>
											<strong>${movie.title }</strong>
										</h3>
										<div class="infor_btn">
											<a
												href="<c:url value='/movie/detail'>
                           <c:param name='movie_id' value='${movie.movie_id}'/>
                             </c:url>">
												상세정보</a> <a
												href="<c:url value='/movie/timelist'>
                              <c:param name='movie_id' value='${movie.movie_id}' />
                              </c:url> ">
												예매하기</a>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>

						<div class="chart_cont3">
							<c:forEach var="movie" items="${movieList}" begin="8" end="11">
								<div>
									<div class="poster">
										<figure>
											<img src="<c:url value='${movie.movie_img}' />"
												alt=${movie.title } width=100%>
										</figure>
										<div class="rank">
											<strong><%=i++%></strong>
										</div>
									</div>
									<div class="infor">
										<h3>
											<strong>${movie.title }</strong>
										</h3>
										<div class="infor_btn">
											<a
												href="<c:url value='/movie/detail'>
                           <c:param name='movie_id' value='${movie.movie_id}'/>
                             </c:url>">
												상세정보</a> <a
												href="<c:url value='/movie/timelist'>
                              <c:param name='movie_id' value='${movie.movie_id}' />
                              </c:url> ">
												예매하기</a>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>

						<div class="chart_cont4">
							<c:forEach var="movie" items="${movieList}" begin="12" end="15">
								<div>
									<div class="poster">
										<figure>
											<img src="<c:url value='${movie.movie_img}' />"
												alt=${movie.title } width=100%>
										</figure>
										<div class="rank">
											<strong><%=i++%></strong>
										</div>
									</div>
									<div class="infor">
										<h3>
											<strong>${movie.title }</strong>
										</h3>
										<div class="infor_btn">
											<a
												href="<c:url value='/movie/detail'>
                           <c:param name='movie_id' value='${movie.movie_id}'/>
                             </c:url>">
												상세정보</a> <a
												href="<c:url value='/movie/timelist'>
                              <c:param name='movie_id' value='${movie.movie_id}' />
                              </c:url> ">
												예매하기</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!--  영화차트 탭 메뉴-->
	<script>
        var movBtn = $(".movie_title > ul > li");    
        var movCont = $(".movie_chart > div");  

        movCont.hide().eq(0).show();

        movBtn.click(function(e){
            e.preventDefault();
            var target = $(this);         
            var index = target.index();  
            movBtn.removeClass("active");   
            target.addClass("active");    
            movCont.css("display","none");
            movCont.eq(index).css("display","block");
        });
        
    </script>


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