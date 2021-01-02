package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Movie;
import model.Time;

public class MovieDao {

//  private JDBCUtil jdbcUtil = null;

   public MovieDao() {
      // TODO Auto-generated constructor stub
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // jdbcUtil = new JDBCUtil();
      } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
      }
   }

   private static Connection getConnection() {
      String url = // "jdbc:oracle:thin:@localhost:1521:xe";
            "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
      String user = "dbprog0202";
      String passwd = "dbprog0202";

      // DBMS와의 연결 획득
      Connection conn = null;
      try {
         conn = DriverManager.getConnection(url, user, passwd);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return conn;
   }

   // 추가(삽입)
   public int insert(Movie mto) {
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;

      String query = "INSERT INTO MOVIE (movie_id, title, genre, release_date, movie_info ) "
            + "VALUES (movie_seq.nextval, ?, ?, ?, ? ) ";

      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);

         pStmt.setString(1, mto.getTitle());
         pStmt.setString(2, mto.getGenre());
         pStmt.setDate(3, mto.getRelease_date());
         pStmt.setString(4, mto.getMovie_info());

         return pStmt.executeUpdate();

      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally { // 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (pStmt != null)
            try {
               pStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
      }
      return -1;
   }

   // 수정(업데이트)
   public int update(Movie mto) {

      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;

      String sql = "update movie " + "set title = ?, genre = ?, release_date = ?, " + "movie_info = ? "
            + "where movie_id = ?";

      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);

         pStmt.setString(1, mto.getTitle());
         pStmt.setString(2, mto.getGenre());
         pStmt.setDate(3, mto.getRelease_date());
         pStmt.setString(4, mto.getMovie_info());
         pStmt.setInt(5, mto.getMovie_id());

         return pStmt.executeUpdate();

      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally { // 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (pStmt != null)
            try {
               pStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
      }
      return -1;
   }

   // 삭제(delete)
   public int delete(int movie_id) {
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;

      String query = "DELETE FROM movie " + "WHERE movie_id = ?";
      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         pStmt.setInt(1, movie_id);

         return pStmt.executeUpdate();

      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally { // 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (pStmt != null)
            try {
               pStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
      }
      return -1;
   }

   // 검색
   public List<Movie> movieSearch(String title){
       Connection conn = null;
       PreparedStatement pStmt = null;
       ResultSet rs = null; 

       ArrayList<Movie> list = new ArrayList<Movie>();

       //영화에 맞는 시간들을 구하는 쿼리
       String query = "SELECT * from movie " + 
    		   		"WHERE title LIKE '%"+title+"%' ";
       
       System.out.println(query);
       try {
          conn = getConnection(); 
          pStmt = conn.prepareStatement(query);
          //pStmt.setString(1, title);
          rs = pStmt.executeQuery();
          
          while (rs.next()) {
        	 Movie mto = new Movie();
             mto.setMovie_id(rs.getInt("movie_id"));
             mto.setTitle(rs.getString("title"));
             mto.setGenre(rs.getString("genre"));
             mto.setMovie_info(rs.getString("movie_info"));
             mto.setRelease_date(rs.getDate("release_date"));
             mto.setMovie_img(rs.getString("movie_img"));

             list.add(mto);
          }
          
          System.out.println(list.size());

          return list;

       }catch(SQLException ex) {
          ex.printStackTrace();
       } finally {      // 자원 반납
          if (rs != null) 
             try { 
                rs.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
          if (pStmt != null) 
             try { 
                pStmt.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
          if (conn != null) 
             try { 
                conn.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
       }
       return null;
    }
   
   // 모두 선택
   public List<Movie> selectAll() {
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;

      String query = "SELECT * From movie order by movie_id";
      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         rs = pStmt.executeQuery();

         List<Movie> list = new ArrayList<Movie>();

         while (rs.next()) {
            Movie mto = new Movie();
            mto.setMovie_id(rs.getInt("movie_id"));
            mto.setTitle(rs.getString("title"));
            mto.setGenre(rs.getString("genre"));
            mto.setMovie_info(rs.getString("movie_info"));
            mto.setRelease_date(rs.getDate("release_date"));
            mto.setMovie_img(rs.getString("movie_img"));

            list.add(mto);
         }
         return list;

      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally { // 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (pStmt != null)
            try {
               pStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
      }
      return null;

   }


   // find

   public Movie findMovie(int movieId) {
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;

      String sql = "SELECT * " + "FROM movie " + "WHERE movie_id=? ";

      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setInt(1, movieId);
         rs = pStmt.executeQuery();

         while (rs.next()) {
            Movie movie = new Movie();
            movie.setMovie_id(movieId);
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setMovie_info(rs.getString("movie_info"));
            movie.setRelease_date(rs.getDate("release_date"));
            movie.setMovie_img(rs.getString("movie_img"));

            return movie;
         }

      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally { // 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (pStmt != null)
            try {
               pStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
      }
      return null;
   }

   // 시간 목록 select 하는 질의 쓸 것 + 관 정보
   public List<Time> selectTimeList(int movie_id){
       Connection conn = null;
       PreparedStatement pStmt = null;
       ResultSet rs = null; 

       ArrayList<Time> list = new ArrayList<Time>();

       //영화에 맞는 시간들을 구하는 쿼리
       String query = "SELECT time, theater_name, num, time_id, title "
             + "FROM movie m JOIN movie_time mt USING (movie_id) JOIN theater t USING (theater_id) "
             + "WHERE movie_id = ? "
             + "ORDER BY num";
       
       System.out.println(query);
       try {
          conn = getConnection(); 
          pStmt = conn.prepareStatement(query);
          pStmt.setInt(1, movie_id);

          
          rs = pStmt.executeQuery();
          

          while (rs.next()) {
             Time t = new Time();

             t.setNum(rs.getInt("num"));
             t.setTime(rs.getString("time"));
             t.setTheater_name(rs.getString("theater_name"));
             t.setTime_id(rs.getInt("time_id"));
             t.setTitle(rs.getString("title"));

             list.add(t);
          }
          
          System.out.println(list.size());

          return list;

       }catch(SQLException ex) {
          ex.printStackTrace();
       } finally {      // 자원 반납
          if (rs != null) 
             try { 
                rs.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
          if (pStmt != null) 
             try { 
                pStmt.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
          if (conn != null) 
             try { 
                conn.close(); 
             } catch (SQLException ex) { ex.printStackTrace(); }
       }
       return null;
    }

}