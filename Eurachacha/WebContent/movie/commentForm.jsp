<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>리뷰 작성</title>
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
  <script>
   function commCreate(){
      if(form.content.value == ""){
         alert("내용을 입력해주세요.");
         form.content.focus();
         return false;
      }
      form.submit();
   }
</script>
<style>
   .content{
      width: 500px;
   }
   body{
      padding: 30px
   }
</style>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/movie/comment/create'/>">
   <input type="hidden" name="movie_id" value="${movie_id.movie_id}" >
   <!-- 여기서 movie_id가 안읽히는것 같음 -->
   <div class="form-group">
       <label for="exampleFormControlTextarea1">리뷰 작성해주세요.</label>
       <textarea class="form-control content" id="exampleFormControlTextarea1" rows="10" name="content"></textarea>
   </div>
   <input type="button" class="btn btn-danger" value="취소" onClick="history.go(-1)"> 
   <input type="submit" class="btn btn-secondary" value="등록" onClick="commCreate()"> 
</form>
</body>
</html>