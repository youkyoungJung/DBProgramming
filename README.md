# Eurachacha
영화 예매 사이트

* ## 주제 및 개요 ##
   * 영화 예매 사이트 구축
   * 일반 영화관에서는 상영하지 않는 영화를 저렴한 가격으로 영화관에서 보고싶은 사람들을 위한 이벤트성 영화관
   
* ## 주요 기능 ##
   * 영화예매
   * 영화 정보 관리
   * 잔여석 관리
   * 리뷰 작성
   * 포인트 룰렛(적립)
  
* ## 구현 기술 ##
   * JSP SERVLET / TOMCAT
   * JDBC
   * ORACLE 12C
   * HTML/CSS
   * BOOTSTRAP
   * MYBATIS

* ### 조인 쿼리 ###
* ```java
*   //영화에 맞는 시간들을 구하는 쿼리
       String query = "SELECT time, theater_name, num, time_id, title "
             + "FROM movie m JOIN movie_time mt USING (movie_id) JOIN theater t USING (theater_id) "
             + "WHERE movie_id = ? "
             + "ORDER BY num";
 ```
