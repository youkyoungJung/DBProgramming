<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/navbar.jsp" />

<script>
   function commRemove() {
      return confirm("정말 삭제하시겠습니까?");      
   }
</script>
<title>영화</title>
<style>

.id {
   width: 150px;
   text-align: center;
}

.content {
   width: 400px;
   padding-left: 10px;
   padding-right: 10px;
}

.edit {
   width: 150px;
   text-align: center;
}

tr {
   height: 100px;
}

table {
   margin-left: auto;
   margin-right: auto;
}

.pagination {
   justify-content: center;
}

.pager {
   text-align: center;
}
      h1 {
      margin: 0px 0px 0px 10%;
      font-family: 'Cafe24Danjunghae'
     }
           h5 {
      margin: 0px 0px 0px 0%;
      font-family: 'Cafe24Danjunghae'
     }
     
</style>


</head>
<body>
   <%
           Random random = new Random();
              int num = random.nextInt(15);
         %>

   <!--상세내용-->
   <h1 class="mr-3 my-3">상세정보</h1>
   <div class="card mb-1">
      <div class="row no-gutters">
         <div class="col-4 d-flex justify-content-center align-items-center">
            <div class="col-4 pr-2">
               <p class="text-center">1</p>
            </div>
            <div class="col-8">
               <img src="<c:url value='${movie.movie_img}'/>" class="card-img"
                  alt="profile" style="width: 100%;" />
            </div>
         </div>
         <div class="col-8">
            <div class="card-body">
               <h5 class="card-title">${movie.title }</h5>
               <p class="card-text">${movie.genre }</p>
               <br>
               <p>${movie.movie_info }</p>

               <p class="card-text">
                  <small class="text-muted">${movie.release_date } 개봉</small>
               </p>
            </div>
         </div>
         <hr style="width: 70px; border: 1px solid orange;">
         <div class="container mt-1"
            style="padding-left: 20px; padding-right: 20px">
            <div class="row">

               <!-- 영화 카드 시작 -->
               <c:forEach var="mv" items="${movieList}" begin="<%=num%>"
                  end="<%=num+2%>">
                  <div class="col-4 px-1" style="width: 18rem;">
                     <div class="card px-0 my-1">
                        <img src="<c:url value='${mv.movie_img }'/>"
                           class="card-img-top" alt="poster" data-target="#movie-modal1"
                           data-toggle="modal" />
                        <div class="card-body">
                           <h5 class="card-title">${mv.title}</h5>
                           <p class="card-text">
                              <small class="text-muted">${mv.release_date}</small>
                           </p>
                        </div>
                     </div>
                  </div>
                  <!-- 영화 카드 끝 -->

                  <!-- 영화 모달 시작-->
                  <div class="modal fade" id="movie-modal1" tabindex="-1"
                     role="dialog" aria-labelledby="MovieModalLabel1"
                     aria-hidden="true">
                     <div class="modal-dialog" role="document" style="width: 450px;">
                        <div class="modal-content">
                           <div class="modal-header">
                              <h5 class="modal-title" id="MovieModalLabel1">${mv.title}</h5>
                              <button type="button" class="close" data-dismiss="modal"
                                 aria-label="Close">
                                 <span aria-hidden="true">&times;</span>
                              </button>
                           </div>
                           <img src="<c:url value='${mv.movie_img }'/>"
                              class="card-img-top" alt="poster" />
                           <div class="modal-body">

                              <p>${mv.movie_info}</p>
                           </div>
                           <div class="modal-footer">
                              <button type="button" class="btn btn-secondary"
                                 data-dismiss="modal">Close</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:forEach>
               <!-- 영화 모달 끝-->
            </div>
         </div>
      </div>
   </div>


   <br>
   <!-- comment 시작 -->

   <div>
      <table class="comment" border="1">
         <c:forEach var="comm" items="${commList }">
            <tr>
               <td class="id">${comm.member_id }</td>
               <td class="content">${comm.reg_date} <br>${comm.content }</td>
               <td class="edit"><a
                  href="<c:url value='/movie/comment/update'>
                  <c:param name="comment_id" value='${comm.id }'/>
                  </c:url>">수정</a>
                  &nbsp; <a
                  href="<c:url value='/movie/comment/remove'>
                  <c:param name="comment_id" value='${comm.id }'/>
                  </c:url>"
                  onclick="return commRemove();">삭제</a></td>
            </tr>
         </c:forEach>
      </table>
      <br>
      <div class="pagination">
         <a class="btn btn-outline-secondary"
            href="<c:url value='/movie/comment/create/form'>
         <c:param name="movie_id" value='${movie.movie_id }'/></c:url>"
            role="button">리뷰작성</a>
      </div>
   </div>
 
   <!--상세 내용 끝 -->
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